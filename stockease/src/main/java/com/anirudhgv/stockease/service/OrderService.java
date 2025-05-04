package com.anirudhgv.stockease.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.errorHandler.ResourceNotFoundException;
import com.anirudhgv.stockease.model.Inventory;
import com.anirudhgv.stockease.model.InventoryLog;
import com.anirudhgv.stockease.model.Order;
import com.anirudhgv.stockease.model.OrderItem;
import com.anirudhgv.stockease.model.Product;
import com.anirudhgv.stockease.model.User;
import com.anirudhgv.stockease.repository.InventoryLogRepository;
import com.anirudhgv.stockease.repository.InventoryRepository;
import com.anirudhgv.stockease.repository.OrderItemRepository;
import com.anirudhgv.stockease.repository.OrderRepository;
import com.anirudhgv.stockease.repository.ProductRepository;
import com.anirudhgv.stockease.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryLogRepository inventoryLogRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    @Transactional
    public Order createOrder(Order order) {
        if (order.getUser() == null || order.getUser().getid() == null) {
            throw new IllegalArgumentException("User is required for placing an order");
        }

        User user = userRepository.findById(order.getUser().getid())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + order.getUser().getid()));

        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0.0;

        Order savedOrder = orderRepository.save(order);

        for (OrderItem item : order.getItems()) {
            Long productId = item.getProduct().getid();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));
            item.setProduct(product);

            Inventory inventory = inventoryRepository.findByProductId(productId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Inventory not found for product ID: " + productId));

            if (inventory.getQuantity() < item.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product ID: " + productId);
            }

            int newQuantity = inventory.getQuantity() - item.getQuantity();
            inventory.setQuantity(newQuantity);
            inventoryRepository.save(inventory);

            InventoryLog log = new InventoryLog();
            log.setProduct(product);
            log.setChangeAmount(-item.getQuantity());
            log.setNewQuantity(newQuantity);
            log.setReason("ORDER");
            log.setTimestamp(LocalDateTime.now());
            log.setUser(user);
            inventoryLogRepository.save(log);

            item.setOrder(savedOrder);
            orderItemRepository.save(item);

            totalAmount += product.getPrice().doubleValue() * item.getQuantity();
        }

        savedOrder.setTotalAmount(BigDecimal.valueOf(totalAmount));
        return orderRepository.save(savedOrder);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existing = getOrderById(id);
        existing.setStatus(updatedOrder.getStatus());
        existing.setTotalAmount(updatedOrder.getTotalAmount());
        existing.setOrderDate(updatedOrder.getOrderDate());
        return orderRepository.save(existing);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Long[] getDashboardData() {
        List<Order> allOrders = orderRepository.findAll();
        Long[] res = new Long[] {
            allOrders.stream().filter(order -> order.getStatus().equals(Order.Status.PENDING)).count(),
            allOrders.stream().filter(order -> order.getStatus().equals(Order.Status.PROCESSING)).count(),
            allOrders.stream().filter(order -> order.getStatus().equals(Order.Status.CONFIRMED)).count()
        };
        return res;
    }
    
}

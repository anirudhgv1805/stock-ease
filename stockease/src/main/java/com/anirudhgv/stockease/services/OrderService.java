package com.anirudhgv.stockease.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.model.Inventory;
import com.anirudhgv.stockease.model.Order;
import com.anirudhgv.stockease.model.OrderItem;
import com.anirudhgv.stockease.repository.InventoryRepository;
import com.anirudhgv.stockease.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<String> checkStockAvailability(Order order) {
        List<String> stockIssues = new ArrayList<>();

        for (OrderItem item : order.getOrderItems()) {
            Inventory inventory = inventoryRepository.findByItem(item.getItem()).orElse(null);

            if (inventory == null) {
                stockIssues.add("No inventory found for item: " + item.getItem().getName());
            } else if (inventory.getStock() < item.getQuantity()) {
                stockIssues.add("Insufficient stock for item: " + item.getItem().getName() +
                        " (Available: " + inventory.getStock() + ", Required: " + item.getQuantity() + ")");
            }
        }

        return stockIssues;
    }

    public Order createOrder(Order order) {
        order.setStatus("Pending");  
        return orderRepository.save(order);
    }

    public Order processOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        List<String> stockIssues = checkStockAvailability(order);
        if (!stockIssues.isEmpty()) {
            throw new RuntimeException("Stock issues: " + stockIssues.toString());
        }

        for (OrderItem item : order.getOrderItems()) {
            Inventory inventory = inventoryRepository.findByItem(item.getItem())
                    .orElseThrow(() -> new RuntimeException("Inventory not found for item: " + item.getItem().getName()));

            inventory.setStock(inventory.getStock() - item.getQuantity());
            inventoryRepository.save(inventory);
        }

        order.setStatus("Completed");
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}

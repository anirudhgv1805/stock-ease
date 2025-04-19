package com.anirudhgv.stockease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.errorHandler.ResourceNotFoundException;
import com.anirudhgv.stockease.model.Order;
import com.anirudhgv.stockease.model.OrderItem;
import com.anirudhgv.stockease.repository.OrderItemRepository;
import com.anirudhgv.stockease.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

     public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

            for (OrderItem item : order.getItems()) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

        return savedOrder;
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
}

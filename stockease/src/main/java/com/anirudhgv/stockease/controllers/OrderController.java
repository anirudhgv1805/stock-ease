package com.anirudhgv.stockease.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.Order;
import com.anirudhgv.stockease.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/validate")
    public ResponseEntity<?> validateOrder(@RequestBody Order order) {
        List<String> issues = orderService.checkStockAvailability(order);
        if (!issues.isEmpty()) {
            return ResponseEntity.badRequest().body(issues);
        }
        return ResponseEntity.ok("✅ All items have sufficient stock.");
    }

    @PostMapping("/add")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        List<String> issues = orderService.checkStockAvailability(order);
        if (!issues.isEmpty()) {
            return ResponseEntity.badRequest().body(issues);
        }
        Order saved = orderService.createOrder(order);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/process/{orderId}")
    public ResponseEntity<?> processOrder(@PathVariable Long orderId) {
        try {
            Order processed = orderService.processOrder(orderId);
            return ResponseEntity.ok("Order processed: " + processed.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Could not Process the order" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}

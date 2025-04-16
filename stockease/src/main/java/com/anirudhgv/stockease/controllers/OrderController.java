package com.anirudhgv.stockease.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.Order;
import com.anirudhgv.stockease.services.OrderService;


@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> fetchOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/orders/add")
    public void getOrders(@RequestBody Order order) {
        orderService.createOrder(order);
        return;
    }
    
}

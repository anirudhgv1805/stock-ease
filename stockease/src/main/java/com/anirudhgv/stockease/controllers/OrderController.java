package com.anirudhgv.stockease.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.Order;

@RestController
@RequestMapping("/api")
public class OrderController {

    @GetMapping("/orders")
    public List<Order> fetchOrders() {
        List<Order> ordersList = new ArrayList<>();
        Order o1 = new Order("bag",10,"ABC Textiles");
        ordersList.add(o1);
        return ordersList;
    }

}

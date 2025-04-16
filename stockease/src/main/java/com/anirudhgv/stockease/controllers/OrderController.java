package com.anirudhgv.stockease.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.Order;


@RestController
@RequestMapping("/api")
public class OrderController {

    class ManageOrders{
        ManageOrders() {}
        public List<Order> ordersList = new ArrayList<>();
        public Order o1 = new Order("bag", 10, "ABC Textiles");
    }

    ManageOrders m = new ManageOrders();
    List<Order> ordersList = m.ordersList;

    @GetMapping("/orders")
    public List<Order> fetchOrders() {
        ordersList.add(m.o1);
        System.out.println(ordersList);
        return ordersList;
    }

    @PostMapping("/orders/add")
    public void getOrders(@RequestBody Order order) {
        m.ordersList.add(order);
        System.out.println(order.getClientName());
    }
    
}

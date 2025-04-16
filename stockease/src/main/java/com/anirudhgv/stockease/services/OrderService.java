package com.anirudhgv.stockease.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.model.Order;
import com.anirudhgv.stockease.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Boolean createOrder(Order order){
        orderRepository.save(order);
        return true;
    }
}

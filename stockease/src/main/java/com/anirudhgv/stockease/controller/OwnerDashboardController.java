package com.anirudhgv.stockease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.response.OwnerDashboardData;
import com.anirudhgv.stockease.service.OrderService;


@RestController
@RequestMapping("/api/owner")
public class OwnerDashboardController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping("/salescount")
    public ResponseEntity<Integer> getSalesOfTheMonth() {
        return ResponseEntity.ok().body(124);
    }

    @GetMapping("/orderscount")
    public ResponseEntity<Integer> getOrdersCount() {
        return ResponseEntity.ok().body(300);
    }

    @GetMapping("/dashboardData")
    public ResponseEntity<OwnerDashboardData> getDashboardData() {
        return ResponseEntity.ok().body(new OwnerDashboardData(orderService.getDashboardData()));
    }
    

}

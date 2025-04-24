package com.anirudhgv.stockease.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/insights")
public class InsightController {

    @GetMapping("/")
    public Integer getNoOrders() {
        return 10;
    }
    
}

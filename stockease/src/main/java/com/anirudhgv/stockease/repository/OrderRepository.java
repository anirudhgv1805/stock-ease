package com.anirudhgv.stockease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anirudhgv.stockease.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}

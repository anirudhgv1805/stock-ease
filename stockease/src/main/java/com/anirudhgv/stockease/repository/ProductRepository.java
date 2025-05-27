package com.anirudhgv.stockease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anirudhgv.stockease.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

package com.anirudhgv.stockease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anirudhgv.stockease.model.ProductionBatch;

public interface ProductionBatchRepository extends JpaRepository<ProductionBatch, Long> {

}

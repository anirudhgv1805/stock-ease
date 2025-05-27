package com.anirudhgv.stockease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anirudhgv.stockease.model.ProductionBatch;

@Repository
public interface ProductionBatchRepository extends JpaRepository<ProductionBatch, Long> {

}

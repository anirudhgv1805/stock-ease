package com.anirudhgv.stockease.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anirudhgv.stockease.model.InventoryLog;

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {

    List<InventoryLog> findByProductId(Long productId);

}

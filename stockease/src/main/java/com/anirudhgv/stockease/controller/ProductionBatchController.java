package com.anirudhgv.stockease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anirudhgv.stockease.model.ProductionBatch;
import com.anirudhgv.stockease.service.ProductionBatchService;

@RestController
@RequestMapping("/api/production-batches")
public class ProductionBatchController {

    @Autowired
    private ProductionBatchService productionBatchService;

    @GetMapping
    public List<ProductionBatch> getAllBatches() {
        return productionBatchService.getAllBatches();
    }

    @GetMapping("/{id}")
    public ProductionBatch getBatchById(@PathVariable Long id) {
        return productionBatchService.getBatchById(id);
    }

    @PostMapping
    public ProductionBatch createBatch(@RequestBody ProductionBatch productionBatch) {
        return productionBatchService.createProductionBatch(productionBatch);
    }

    @PutMapping("/{id}")
    public ProductionBatch updateBatch(@PathVariable Long id, @RequestBody ProductionBatch productionBatch) {
        return productionBatchService.updateBatch(id, productionBatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBatch(@PathVariable Long id) {
        productionBatchService.deleteBatch(id);
        return ResponseEntity.ok().build();
    }
}

package com.anirudhgv.stockease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.errorHandler.ResourceNotFoundException;
import com.anirudhgv.stockease.model.ProductionBatch;
import com.anirudhgv.stockease.repository.ProductionBatchRepository;

@Service
public class ProductionBatchService {

    @Autowired
    private ProductionBatchRepository productionBatchRepository;

    public List<ProductionBatch> getAllBatches() {
        return productionBatchRepository.findAll();
    }

    public ProductionBatch getBatchById(Long id) {
        return productionBatchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Production batch not found with id " + id));
    }

    public ProductionBatch createProductionBatch(ProductionBatch productionBatch) {
        return productionBatchRepository.save(productionBatch);
    }

    public ProductionBatch updateBatch(Long id, ProductionBatch updatedBatch) {
        ProductionBatch existing = getBatchById(id);
        existing.setProduct(updatedBatch.getProduct());
        existing.setQuantityProduced(updatedBatch.getQuantityProduced());
        existing.setProductionDate(updatedBatch.getProductionDate());
        existing.setNotes(updatedBatch.getNotes());
        return productionBatchRepository.save(existing);
    }

    public void deleteBatch(Long id) {
        productionBatchRepository.deleteById(id);
    }
}

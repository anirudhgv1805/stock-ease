package com.anirudhgv.stockease.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anirudhgv.stockease.errorHandler.ResourceNotFoundException;
import com.anirudhgv.stockease.model.Product;
import com.anirudhgv.stockease.model.dto.ProductUpdateRequest;
import com.anirudhgv.stockease.repository.ProductRepository;

import aj.org.objectweb.asm.commons.LocalVariablesSorter;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductUpdateRequest request) {
        Product existing = getProductById(id);
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setSku(request.getSku());
        existing.setPrice(request.getPrice());
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

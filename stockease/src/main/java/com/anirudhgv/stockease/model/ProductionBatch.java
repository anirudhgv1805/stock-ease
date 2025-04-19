package com.anirudhgv.stockease.model;


import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "production_batches")
public class ProductionBatch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantityProduced;

    private LocalDate productionDate;

    private String notes;


    public ProductionBatch() {
    }

    public ProductionBatch(Long batchId, Product product, Integer quantityProduced, LocalDate productionDate, String notes) {
        this.batchId = batchId;
        this.product = product;
        this.quantityProduced = quantityProduced;
        this.productionDate = productionDate;
        this.notes = notes;
    }

    public Long getBatchId() {
        return this.batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantityProduced() {
        return this.quantityProduced;
    }

    public void setQuantityProduced(Integer quantityProduced) {
        this.quantityProduced = quantityProduced;
    }

    public LocalDate getProductionDate() {
        return this.productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ProductionBatch batchId(Long batchId) {
        setBatchId(batchId);
        return this;
    }

    public ProductionBatch product(Product product) {
        setProduct(product);
        return this;
    }

    public ProductionBatch quantityProduced(Integer quantityProduced) {
        setQuantityProduced(quantityProduced);
        return this;
    }

    public ProductionBatch productionDate(LocalDate productionDate) {
        setProductionDate(productionDate);
        return this;
    }

    public ProductionBatch notes(String notes) {
        setNotes(notes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductionBatch)) {
            return false;
        }
        ProductionBatch productionBatch = (ProductionBatch) o;
        return Objects.equals(batchId, productionBatch.batchId) && Objects.equals(product, productionBatch.product) && Objects.equals(quantityProduced, productionBatch.quantityProduced) && Objects.equals(productionDate, productionBatch.productionDate) && Objects.equals(notes, productionBatch.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(batchId, product, quantityProduced, productionDate, notes);
    }

    @Override
    public String toString() {
        return "{" +
            " batchId='" + getBatchId() + "'" +
            ", product='" + getProduct() + "'" +
            ", quantityProduced='" + getQuantityProduced() + "'" +
            ", productionDate='" + getProductionDate() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
    
}

package com.anirudhgv.stockease.model;


import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "production_inputs")
public class ProductionInput {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inputId;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private ProductionBatch batch;

    private String rawMaterial;

    private BigDecimal quantityUsed;

    private String unit;


    public ProductionInput() {
    }

    public ProductionInput(Long inputId, ProductionBatch batch, String rawMaterial, BigDecimal quantityUsed, String unit) {
        this.inputId = inputId;
        this.batch = batch;
        this.rawMaterial = rawMaterial;
        this.quantityUsed = quantityUsed;
        this.unit = unit;
    }

    public Long getInputId() {
        return this.inputId;
    }

    public void setInputId(Long inputId) {
        this.inputId = inputId;
    }

    public ProductionBatch getBatch() {
        return this.batch;
    }

    public void setBatch(ProductionBatch batch) {
        this.batch = batch;
    }

    public String getRawMaterial() {
        return this.rawMaterial;
    }

    public void setRawMaterial(String rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public BigDecimal getQuantityUsed() {
        return this.quantityUsed;
    }

    public void setQuantityUsed(BigDecimal quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ProductionInput inputId(Long inputId) {
        setInputId(inputId);
        return this;
    }

    public ProductionInput batch(ProductionBatch batch) {
        setBatch(batch);
        return this;
    }

    public ProductionInput rawMaterial(String rawMaterial) {
        setRawMaterial(rawMaterial);
        return this;
    }

    public ProductionInput quantityUsed(BigDecimal quantityUsed) {
        setQuantityUsed(quantityUsed);
        return this;
    }

    public ProductionInput unit(String unit) {
        setUnit(unit);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductionInput)) {
            return false;
        }
        ProductionInput productionInput = (ProductionInput) o;
        return Objects.equals(inputId, productionInput.inputId) && Objects.equals(batch, productionInput.batch) && Objects.equals(rawMaterial, productionInput.rawMaterial) && Objects.equals(quantityUsed, productionInput.quantityUsed) && Objects.equals(unit, productionInput.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId, batch, rawMaterial, quantityUsed, unit);
    }

    @Override
    public String toString() {
        return "{" +
            " inputId='" + getInputId() + "'" +
            ", batch='" + getBatch() + "'" +
            ", rawMaterial='" + getRawMaterial() + "'" +
            ", quantityUsed='" + getQuantityUsed() + "'" +
            ", unit='" + getUnit() + "'" +
            "}";
    }
    
}

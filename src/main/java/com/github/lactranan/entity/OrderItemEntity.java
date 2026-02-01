package com.github.lactranan.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class OrderItemEntity {

    private String sku;
    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}

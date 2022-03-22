package com.copernic.cat.erp.sales_4_us.models;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LiniaPurchaseId implements Serializable {
    private static final long serialVersionUID = -7022609050242200644L;
    @Column(name = "PURCHASE_ID", nullable = false)
    private Integer purchaseId;
    @Column(name = "PRODUCT_ID", nullable = false)
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, purchaseId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LiniaPurchaseId entity = (LiniaPurchaseId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.purchaseId, entity.purchaseId);
    }
}
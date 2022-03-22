package com.copernic.cat.erp.sales_4_us.models;

import javax.persistence.*;

@Entity
@Table(name = "linia_purchase")
public class LiniaPurchase {
    @EmbeddedId
    private LiniaPurchaseId id;
    @MapsId("purchaseId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PURCHASE_ID")
    private Purchase purchase;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public LiniaPurchaseId getId() {
        return id;
    }

    public void setId(LiniaPurchaseId id) {
        this.id = id;
    }

}
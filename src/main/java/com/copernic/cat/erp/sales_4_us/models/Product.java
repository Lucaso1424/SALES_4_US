package com.copernic.cat.erp.sales_4_us.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "PROVIDER_ID", nullable = false)
    private Integer providerId;

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Column(name = "PRIZE", nullable = false, precision = 10)
    private BigDecimal prize;

    @Column(name = "CATEGORY", nullable = false, length = 40)
    private String category;

    @Column(name = "DESCRIPTION", nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "product")
    private Set<LiniaPurchase> liniaPurchases = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Opinion> opinions = new LinkedHashSet<>();

    public Set<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(Set<Opinion> opinions) {
        this.opinions = opinions;
    }

    public Set<LiniaPurchase> getLiniaPurchases() {
        return liniaPurchases;
    }

    public void setLiniaPurchases(Set<LiniaPurchase> liniaPurchases) {
        this.liniaPurchases = liniaPurchases;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
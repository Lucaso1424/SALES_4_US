package com.copernic.cat.erp.sales_4_us.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DATE", nullable = false)
    private Instant date;

    @Column(name = "TOTAL_PRIZE", nullable = false, precision = 10)
    private BigDecimal totalPrize;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "purchase")
    private Set<LiniaPurchase> liniaPurchases = new LinkedHashSet<>();

    public Set<LiniaPurchase> getLiniaPurchases() {
        return liniaPurchases;
    }

    public void setLiniaPurchases(Set<LiniaPurchase> liniaPurchases) {
        this.liniaPurchases = liniaPurchases;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(BigDecimal totalPrize) {
        this.totalPrize = totalPrize;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
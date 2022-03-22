package com.copernic.cat.erp.sales_4_us.repository;

import com.copernic.cat.erp.sales_4_us.models.LiniaPurchase;
import com.copernic.cat.erp.sales_4_us.models.LiniaPurchaseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiniaPurchaseRepository extends JpaRepository<LiniaPurchase, LiniaPurchaseId> {
}
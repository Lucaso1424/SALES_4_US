package com.copernic.cat.erp.sales_4_us.repository;

import com.copernic.cat.erp.sales_4_us.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
}
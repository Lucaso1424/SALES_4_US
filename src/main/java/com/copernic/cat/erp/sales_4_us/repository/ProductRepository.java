package com.copernic.cat.erp.sales_4_us.repository;

import com.copernic.cat.erp.sales_4_us.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
        Product findProductById(Long id);

        @Query(value = "select * from product p where p.name like %:keyword%", nativeQuery = true)
        List<Product> findByKeyword(@Param("keyword") String keyword);
}
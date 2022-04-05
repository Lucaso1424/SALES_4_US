package com.copernic.cat.erp.sales_4_us.repository;

import com.copernic.cat.erp.sales_4_us.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
        // Repository de productes amb mètodes de JpaRepository

        // Mètode findProductById passant per paràmetre id de producte
        Product findProductById(Long id);

        //Mètode findByKeyword(), amb anotació @Query, per trobar els productes
        // de la BBDD en base al text passat per paràmetre, realitzant una consulta SELECT
        // a la taula product
        @Query(value = "select * from product p where p.name like %:keyword%", nativeQuery = true)
        List<Product> findByKeyword(@Param("keyword") String keyword);
}
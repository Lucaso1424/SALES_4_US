package com.copernic.cat.erp.sales_4_us.repository;

import com.copernic.cat.erp.sales_4_us.models.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserLogin, Long> {
    UserLogin findUserByEmail(String email);
}
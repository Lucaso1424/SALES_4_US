package com.copernic.cat.erp.sales_4_us.repository;

import com.copernic.cat.erp.sales_4_us.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Repository d'usuaris amb mètodes de JpaRepository

    // Mètode findUserById passant per paràmetre mail de l'usuari
    User findUserByEmail(String email);

    public User findByResetPasswordToken(String token);

}
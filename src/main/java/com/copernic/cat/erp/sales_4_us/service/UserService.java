package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}

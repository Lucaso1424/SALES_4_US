package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service("userDetailsService")
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User usuari = userRepository.findUserByEmail(email);

        if (usuari == null) {
            throw new UsernameNotFoundException(email);
        }
        var rols = new ArrayList<GrantedAuthority>();
        rols.add(new SimpleGrantedAuthority(usuari.getRol()));
        return new org.springframework.security.core.userdetails.User(usuari.getEmail(), usuari.getPassword(), rols);
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /*@Transactional
    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        return users;
    }*/

}

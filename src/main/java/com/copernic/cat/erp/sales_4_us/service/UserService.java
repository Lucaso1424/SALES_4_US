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

import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        return users;
    }

    public List<User> listAdmins(){
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        List<User> admins = new ArrayList<>();
        for (User u : users) {
            if (u.getRol().equals("admin")){
                admins.add(u);
            }
        }
        return admins;
    }

    public List<User> listClients(){
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        List<User> client = new ArrayList<>();
        for (User u : users) {
            if (u.getRol().equals("client")){
                client.add(u);
            }
        }
        return client;
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User searchUser(User user) {
        return userRepository.findById(user.getUserId()).orElse(null);
    }

    @Transactional(readOnly = true)
    public User findUserByEmail(String email){
       return userRepository.findUserByEmail(email);
    }


}

package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

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

        //Retornem l'usuari buscat per email
        User usuari = userRepository.findUserByEmail(email);
        if (usuari == null) {
            throw new UsernameNotFoundException(email);
        }
        //Creem el nou rol per a l'usuari
        var rols = new ArrayList<GrantedAuthority>();
        //Li afegim el rol que li hem proporcionat al usuari
        rols.add(new SimpleGrantedAuthority(usuari.getRol()));
        return new org.springframework.security.core.userdetails.User(usuari.getEmail(), usuari.getPassword(), rols);
    }

    //Llistem tots els usuaris de la BD
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        return users;
    }

    //LListem tots els usuaris amb rol admin de la BD
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

    //LListem tots els usuaris amb rol client de la BD
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

    //Borrem l'usuari
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    //Guardem l'usuari a la base de dades
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    //Busquem l'usuari per l'id
    @Transactional(readOnly = true)
    public User searchUser(User user) {
        return userRepository.findById(user.getUserId()).orElse(null);
    }

    //busquem l'usuari per l'email
    @Transactional(readOnly = true)
    public User findUserByEmail(String email){
       return userRepository.findUserByEmail(email);
    }

    /*Forgot password methods*/

    public void updateResetPasswordToken(String token, String email) throws NotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("Could not find any customer with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }
    
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

}

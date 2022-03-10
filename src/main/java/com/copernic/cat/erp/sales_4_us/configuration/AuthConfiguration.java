package com.copernic.cat.erp.sales_4_us.configuration;

import com.copernic.cat.erp.sales_4_us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserService userDetailsService; //Objecte per recuperar l'usuari

    @Autowired
    public void authentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin") //URL i subURLS (**) on pot accedir...
                .hasAuthority("admin") //...l'usuari amb rol veterinari
                .antMatchers("/") //URL inici on poden accedir...
                .hasAnyAuthority("admin", "client") //...els usuaris amb rol veterinari i pacient
                .and()
                .formLogin() //Objecte que representa el formulari de login personalitzat que utilitzarem
                .loginPage("/login") //Pàgina on es troba el formulari per fer login personalitzat
                .and()
                .exceptionHandling().accessDeniedPage("/errors/error403"); //Mostrarem la pàgina error403 si l'usuari no té accés a una àgina o acció 
    }
    
}

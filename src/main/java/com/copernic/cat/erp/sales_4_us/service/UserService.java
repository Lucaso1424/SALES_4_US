package com.copernic.cat.erp.sales_4_us.service;

import com.copernic.cat.erp.sales_4_us.models.Rol;
import com.copernic.cat.erp.sales_4_us.models.User;
import com.copernic.cat.erp.sales_4_us.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author fta
 */

/*Anotació que permet al sistema que reconegui aquesta classe com una classe de servei
 *i en concret una classe de servei que utilitzara Spring Security, per això passem per
 *paràmetre el nom predeterminat "userDetailsService".
 */
@Service ("userDetailsService")
@Slf4j
public class UserService implements UserDetailsService{


    /*Atribut que defineix un UsuariDAO, injectant els seu mètodes a aquesta classe (@Autowired),
     *els quals ens permeten interactuar amb les taules de la BBDD pels usuaris i rols
     */
    @Autowired
    private UserRepository userRepository;

    /*Únic mètode de la interface UserDetailsService que retornarà un usuari a partir del nom d'usuari.
     *L'usuari que retorna es de tipus UserDetails que és una interface de Spring Security que defineix
     *els mètodes necessaris per treballar amb un usuari.
     */
    @Override
    @Transactional(readOnly=true) //Consulta només de lectura
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        /*Implementem el mètode definits en UsuariDao. Hem de pensar que aquest és un mètode predefinit
         *de Spring Security i, per tant, no hem de desnvolupar cap codi, ja ve donat per Spring Security.
         *Aquest mètode ens retornarà l'usuari a partir de nom d'usuari passat per paràmetre.
         */
        User usuari= userRepository.findUserByEmail(email);

        //Comprovem que existeix l'usuari
        if(usuari==null){ //Si no existeix l'usuari...
            //Llancem una excepció de tipus UsernameNotFoundException
            throw new UsernameNotFoundException(email);

        }

        var rols= new ArrayList<GrantedAuthority>();

        for(Rol rol: usuari.getRols()){
            rols.add(new SimpleGrantedAuthority(rol.getName()));
        }

        /*Retornme el nou usuari de tipus UserDetails mitjançant la classe User d'Spring Security,
         *la qual implementa la interface UserDetails.
         *Com a paràmetres passem el nom d'usuari, la contrasenya i els rols del l'usuari alqual
         *li correspon el nom d'usuari passat com a paràmetre.
         */
        return new org.springframework.security.core.userdetails.User(usuari.getEmail(), usuari.getPassword(), rols);
    }

}

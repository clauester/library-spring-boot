package com.example.biblioteca.service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.biblioteca.persistance.entities.UserEntity;
import com.example.biblioteca.persistance.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    private UserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                                    .orElseThrow(() -> new UsernameNotFoundException("El usuario: "+username+" no existe"));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName())) ).collect(Collectors.toSet()) ;

        User usuario = new User(userEntity.getUsername(), 
                                userEntity.getPassword(), 
                                true, 
                                true, 
                                true, 
                                true, 
                                authorities);
        return usuario;
    } 
    
}

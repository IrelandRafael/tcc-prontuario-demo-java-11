package com.unigranead.tcc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.repositories.LoginRepository;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private LoginRepository repo;
 
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login user = repo.findByUsuario(username);
        if (user != null) {
            return new User(user.getUsuario(), user.getSenha(), buildSimpleGrantedAuthorities(user.getPermissao()));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
 
    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final String permisssao) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(permisssao));
        return authorities;
    }
}

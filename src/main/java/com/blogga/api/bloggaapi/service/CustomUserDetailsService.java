package com.blogga.api.bloggaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.blogga.api.bloggaapi.model.User;
import com.blogga.api.bloggaapi.repository.UserRepository;
import com.blogga.api.bloggaapi.util.JwtUtil;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtUtil jwt;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                new ArrayList<>());
    }

    public User getUserByToken(String token) {
        return repository.findByUserName(jwt.extractUsername(token.substring(7)));
    }
}
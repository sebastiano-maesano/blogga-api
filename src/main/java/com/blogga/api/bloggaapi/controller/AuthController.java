package com.blogga.api.bloggaapi.controller;

import com.blogga.api.bloggaapi.exception.BadRequestException;
import com.blogga.api.bloggaapi.repository.UserRepository;
import com.blogga.api.bloggaapi.request.LoginRequest;
import com.blogga.api.bloggaapi.request.RegisterRequest;
import com.blogga.api.bloggaapi.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest authRequest) throws BadRequestException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new BadRequestException("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) throws BadRequestException {
        return "qualcosa farò";
    }
}
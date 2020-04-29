package com.blogga.api.bloggaapi.controller;

import com.blogga.api.bloggaapi.config.SecurityConfig;
import com.blogga.api.bloggaapi.exception.BadRequestException;
import com.blogga.api.bloggaapi.model.User;
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
    @Autowired
    private SecurityConfig securityConfig;

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
    public void register(@RequestBody RegisterRequest request) throws BadRequestException {
        String encriptedPassword = this.securityConfig.passwordEncoder().encode(request.getPassword());
        User user = new User();

        user.setPassword(encriptedPassword);
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());

        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }
}
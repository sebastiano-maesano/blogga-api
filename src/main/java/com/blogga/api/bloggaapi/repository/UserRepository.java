package com.blogga.api.bloggaapi.repository;

import com.blogga.api.bloggaapi.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
}
package com.blogga.api.bloggaapi.repository;

import com.blogga.api.bloggaapi.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
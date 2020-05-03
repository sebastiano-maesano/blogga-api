package com.blogga.api.bloggaapi.repository;

import com.blogga.api.bloggaapi.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
package com.blogga.api.bloggaapi.repository;

import java.util.List;

import com.blogga.api.bloggaapi.model.Comment;
import com.blogga.api.bloggaapi.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
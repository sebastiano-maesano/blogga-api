package com.blogga.api.bloggaapi.controller;

import java.util.List;

import com.blogga.api.bloggaapi.exception.BadRequestException;
import com.blogga.api.bloggaapi.model.Post;
import com.blogga.api.bloggaapi.repository.PostRepository;
import com.blogga.api.bloggaapi.request.CreatePostRequest;
import com.blogga.api.bloggaapi.request.UpdatePostRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping("/post")
    public Post create(@RequestBody CreatePostRequest request) throws BadRequestException {
        Post post = new Post();

        post.setName(request.getName());
        post.setBody(request.getBody());

        try {
            postRepository.save(post);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

        return post;
    }

    @GetMapping("/posts")
    public List<Post> readList() {
        return postRepository.findAll();
    }

    @GetMapping("/post/{id}")
    public Post readPost(@PathVariable("id") Long id) {
        return postRepository.getOne(id);
    }

    @PutMapping("/post/{id}")
    public Post update(@PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {

        Post post = postRepository.findById(id).get();

        if (request.getBody() != null) {
            post.setBody(request.getBody());
        }

        if (request.getBody() != null) {
            post.setBody(request.getBody());
        }

        return post;
    }

    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
    }

}
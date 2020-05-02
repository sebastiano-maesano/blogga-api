package com.blogga.api.bloggaapi.controller;

import java.util.List;

import com.blogga.api.bloggaapi.exception.BadRequestException;
import com.blogga.api.bloggaapi.model.Post;
import com.blogga.api.bloggaapi.repository.PostRepository;
import com.blogga.api.bloggaapi.request.CreatePostRequest;
import com.blogga.api.bloggaapi.request.UpdatePostRequest;
import com.blogga.api.bloggaapi.response.HttpResponse;
import com.blogga.api.bloggaapi.service.HttpResponderService;

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
    public HttpResponse<Post> create(@RequestBody CreatePostRequest request) throws BadRequestException {
        Post post = new Post();

        post.setName(request.getName());
        post.setBody(request.getBody());

        try {
            postRepository.save(post);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

        return new HttpResponderService<Post>().ok(post);
    }

    @GetMapping("/posts")
    public HttpResponse<List<Post>> readList() {
        return new HttpResponderService<List<Post>>().ok(postRepository.findAll());
    }

    @GetMapping("/post/{id}")
    public HttpResponse<Post> readPost(@PathVariable("id") Long id) {
        return new HttpResponderService<Post>().ok(postRepository.getOne(id));
    }

    @PutMapping("/post/{id}")
    public HttpResponse<Post> update(@PathVariable("id") Long id, @RequestBody UpdatePostRequest request) {

        Post post = postRepository.findById(id).get();

        if (request.getName() != null) {
            post.setName(request.getName());
        }

        if (request.getBody() != null) {
            post.setBody(request.getBody());
        }

        postRepository.save(post);
        return new HttpResponderService<Post>().ok(post);
    }

    @DeleteMapping("/post/{id}")
    public HttpResponse<Post> delete(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return new HttpResponderService<Post>().ok(null);
    }

}
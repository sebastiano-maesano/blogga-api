package com.blogga.api.bloggaapi.controller;

import java.util.List;

import com.blogga.api.bloggaapi.exception.BadRequestException;
import com.blogga.api.bloggaapi.model.Comment;
import com.blogga.api.bloggaapi.model.Post;
import com.blogga.api.bloggaapi.model.User;
import com.blogga.api.bloggaapi.repository.CommentRepository;
import com.blogga.api.bloggaapi.repository.PostRepository;
import com.blogga.api.bloggaapi.repository.UserRepository;
import com.blogga.api.bloggaapi.request.CreateCommentRequest;
import com.blogga.api.bloggaapi.request.CreatePostRequest;
import com.blogga.api.bloggaapi.request.UpdateCommentRequest;
import com.blogga.api.bloggaapi.request.UpdatePostRequest;
import com.blogga.api.bloggaapi.response.HttpResponse;
import com.blogga.api.bloggaapi.service.HttpResponderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Posts methods
     */

    @PostMapping("/post")
    public HttpResponse<Post> create(@RequestBody CreatePostRequest request,
            @AuthenticationPrincipal UserDetails userDetails) throws BadRequestException {
        Post post = new Post();

        post.setName(request.getName());
        post.setBody(request.getBody());
        post.setUser(userRepository.findByUserName(userDetails.getUsername()));

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

    /**
     * Comments methods
     */

    @PostMapping("/post/{postId}/comment")
    public HttpResponse<Comment> createComment(@PathVariable("postId") Long postId,
            @RequestBody CreateCommentRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        Comment comment = new Comment();
        User user = userRepository.findByUserName(userDetails.getUsername());
        Post post = postRepository.findById(postId).get();

        comment.setLabel(request.getLabel());
        comment.setPost(post);
        comment.setUser(user);

        commentRepository.save(comment);

        return new HttpResponderService<Comment>().ok(comment);
    }

    @GetMapping("/post/{postId}/comments")
    public HttpResponse<List<Comment>> readPostComments(@PathVariable("postId") Long postId) {
        Post post = postRepository.findById(postId).get();
        List<Comment> comments = this.commentRepository.findByPost(post);
        return new HttpResponderService<List<Comment>>().ok(comments);
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public HttpResponse<Comment> updatePostComment(@PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId, @RequestBody UpdateCommentRequest request) {

        Comment comment = commentRepository.findById(commentId).get();
        comment.setLabel(request.getLabel());

        commentRepository.save(comment);
        return new HttpResponderService<Comment>().ok(comment);
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public HttpResponse<Comment> deletePostComment(@PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId) {
        commentRepository.deleteById(commentId);
        return new HttpResponderService<Comment>().ok(null);
    }

}
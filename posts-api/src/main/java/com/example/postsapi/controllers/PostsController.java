package com.example.postsapi.controllers;
import com.example.postsapi.models.Post;
import com.example.postsapi.repositories.PostRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class PostsController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/approved")
    public Iterable<Post> findAllApprovedPosts(){
        return postRepository.findAllByApproved(true);
    }

    @GetMapping("/all")
    public Iterable<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/unapproved")
    public Iterable<Post> findAllUnapprovedPosts(){
        return postRepository.findAllByApproved(false);
    }

    @PostMapping("/unapproved")
    public Post createNewUser(@RequestBody Post postRequest){
        return postRepository.save(postRequest);
    }

    @DeleteMapping("/all/{postId}")
    public HttpStatus deleteUserById(@PathVariable Long postId) throws EmptyResultDataAccessException {
        postRepository.delete(postId);
        return HttpStatus.OK;
    }

    // EXCEPTION HANDLERS
    @ExceptionHandler
    void handleUserNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}

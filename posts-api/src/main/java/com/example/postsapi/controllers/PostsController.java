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
    
}

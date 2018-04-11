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

    
}

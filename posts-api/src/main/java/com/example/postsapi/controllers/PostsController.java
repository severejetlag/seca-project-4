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
    public Post createNewPost(@RequestBody Post postRequest){
        return postRepository.save(postRequest);
    }

    @DeleteMapping("/all/{postId}")
    public HttpStatus deletePostById(@PathVariable Long postId) throws EmptyResultDataAccessException {
        postRepository.delete(postId);
        return HttpStatus.OK;
    }

    @PutMapping("/all/{postId}")
    public Post updatePostById(@PathVariable Long postId, @RequestBody Post postRequest) throws NotFoundException {
        Post postFromDb = postRepository.findOne(postId);

        if (postFromDb == null) {
            throw new NotFoundException("Post with ID of " + postId + " was not found!");
        }

        postFromDb.setTitle(postRequest.getTitle());
        postFromDb.setFirstName(postRequest.getFirstName());
        postFromDb.setLastName(postRequest.getLastName());
        postFromDb.setContactDetails(postRequest.getContactDetails());
        postFromDb.setApproved(postRequest.getApproved());
        postFromDb.setVerified(postRequest.getVerified());
        return postRepository.save(postFromDb);
    }
    

    // EXCEPTION HANDLERS
    @ExceptionHandler
    void handlePostNotFound(
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

package com.example.project3.controllers;

import com.example.project3.models.User;
import com.example.project3.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User findUserById(@PathVariable Long userId) throws NotFoundException {

        User foundUser = userRepository.findOne(userId);

        if (foundUser == null) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }

        return foundUser;
    }

    // Barrowed @RequestParam from this artiicle
    //https://www.journaldev.com/3358/spring-requestmapping-requestparam-pathvariable-example
    @GetMapping("/users/search")
    public User findUserByUserName(@RequestParam("userName") String userName) throws NotFoundException {
        User foundUser = userRepository.findByUserName(userName);
        if(foundUser == null){
            throw new NotFoundException("User with Username of " + userName + " was not found");
        }

        return foundUser;
    }

    @PostMapping("/users")
    public User createNewUser(@RequestBody User userRequest){
        return userRepository.save(userRequest);
    }

    @DeleteMapping("/users/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) throws EmptyResultDataAccessException {
        userRepository.delete(userId);
        return HttpStatus.OK;
    }

    @PutMapping("/users/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User userRequest) throws NotFoundException {
        User userFromDb = userRepository.findOne(userId);

        if (userFromDb == null) {
            throw new NotFoundException("User with ID of " + userId + " was not found!");
        }

        userFromDb.setUserName(userRequest.getUserName());
        userFromDb.setFirstName(userRequest.getFirstName());
        userFromDb.setLastName(userRequest.getLastName());
        userFromDb.setPassword(userRequest.getPassword());
        userFromDb.setNeighborhood(userRequest.getNeighborhood());
        userFromDb.setBio(userRequest.getBio());
        return userRepository.save(userFromDb);
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

package com.example.project4.repositories;

import com.example.project4.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    User findByUserName(String userName);
}
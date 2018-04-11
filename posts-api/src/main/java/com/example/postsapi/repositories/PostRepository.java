package com.example.postsapi.repositories;

import com.example.postsapi.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long>{
    List<Post> findAll();

}

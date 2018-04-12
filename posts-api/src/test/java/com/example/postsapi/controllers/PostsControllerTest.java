package com.example.postsapi.controllers;

import com.example.postsapi.controllers.PostsController;
import com.example.postsapi.models.Post;
import com.example.postsapi.repositories.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PostsController.class)
public class PostsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @MockBean
    private PostRepository mockPostRepository;
    private Post updatedSecondPost;
    private Post newPost;

    @Before
    public void setUp() {
        Post firstPost = new Post(
                "Coming to NYC",
                "Nick",
                "Lee",
                "nhl1013@gmail.com",
                "If you are coming to NYC, these are the things you should know!",
                true,
                true
        );

        Post secondPost = new Post(
                "Local Townhall in Queens",
                "Lick",
                "Nee",
                "severejetlag@gmail.com",
                "There will be a local townhall meeting this evening in Queens",
                false,
                false
        );

        updatedSecondPost = new Post(
                "Local Townhall in Queens UPDATED",
                "Kick",
                "Mee",
                "nhl1013@gmail.com",
                "Updated Post Body",
                true,
                true
        );

        newPost = new Post(
                "new test post",
                "new first name",
                "new last name",
                "new email",
                "new post body",
                false,
                false
        );

        List<Post> mockPosts =
                Stream.of(firstPost, secondPost).collect(Collectors.toList());

        given(mockPostRepository.findAll()).willReturn(mockPosts);
        given(mockPostRepository.findOne(1L)).willReturn(firstPost);
        given(mockPostRepository.findOne(4L)).willReturn(null);

        // Mock out Delete to return EmptyResultDataAccessException for missing post with ID of 4
        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("oh no!", 1234);
        }).when(mockPostRepository).delete(4L);

        given(mockPostRepository.save(updatedSecondPost)).willReturn(updatedSecondPost);
        given(mockPostRepository.save(newPost)).willReturn((newPost));

    }

    @Test
    public void findAllPosts_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllPosts_success_returnAllPostsAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }
    @Test
    public void findAllPosts_success_returnPostNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].title", is("Coming to NYC")));
    }

    @Test
    public void findAllPosts_success_returnFirstNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].firstName", is("Nick")));
    }

    @Test
    public void findAllPosts_success_returnLastNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].lastName", is("Lee")));
    }
}

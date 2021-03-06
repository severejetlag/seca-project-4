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

import java.time.LocalDateTime;
import java.util.Calendar;
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
        Calendar today = Calendar.getInstance();
        Post firstPost = new Post(
                "Coming to NYC",
                "Nick",
                "Lee",
                "nhl1013@gmail.com",
                "If you are coming to NYC, these are the things you should know!",
                true,
                true,
                today
        );

        Post secondPost = new Post(
                "Local Townhall in Queens",
                "Lick",
                "Nee",
                "severejetlag@gmail.com",
                "There will be a local townhall meeting this evening in Queens",
                false,
                false,
                today
        );

        updatedSecondPost = new Post(
                "Local Townhall in Queens UPDATED",
                "Kick",
                "Mee",
                "nhl1013@gmail.com",
                "Updated Post Body",
                true,
                true,
                today
        );

        newPost = new Post(
                "new test post",
                "new first name",
                "new last name",
                "new email",
                "new post body"
        );

        List<Post> mockPosts =
                Stream.of(firstPost, secondPost).collect(Collectors.toList());

        List<Post> mockApprovedPosts = Stream.of(firstPost).collect(Collectors.toList());

        given(mockPostRepository.findAll()).willReturn(mockPosts);
        given(mockPostRepository.findAllByApproved(true)).willReturn(mockApprovedPosts);
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
                .perform(get("/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllPosts_success_returnAllPostsAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/all"))
                .andExpect(jsonPath("$", hasSize(2)));
    }
    @Test
    public void findAllPosts_success_returnPostNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/all"))
                .andExpect(jsonPath("$[0].title", is("Coming to NYC")));
    }

    @Test
    public void findAllPosts_success_returnFirstNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/all"))
                .andExpect(jsonPath("$[0].firstName", is("Nick")));
    }

    @Test
    public void findAllPosts_success_returnLastNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/all"))
                .andExpect(jsonPath("$[0].lastName", is("Lee")));
    }

    @Test
    public void findAllApprovedPosts_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/approved"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllApprovedPosts_success_returnAllPostsAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/approved"))
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    public void findAllApprovedPosts_success_returnPostNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/approved"))
                .andExpect(jsonPath("$[0].title", is("Coming to NYC")));
    }

    @Test
    public void findAllApprovedPosts_success_returnFirstNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/approved"))
                .andExpect(jsonPath("$[0].firstName", is("Nick")));
    }

    @Test
    public void findAllApprovedPosts_success_returnLastNameForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/approved"))
                .andExpect(jsonPath("$[0].lastName", is("Lee")));
    }

    @Test
    public void findAllApprovedPosts_success_returnApprovedForEachPost() throws Exception {

        this.mockMvc
                .perform(get("/approved"))
                .andExpect(jsonPath("$[0].approved", is(true)));
    }

    @Test
    public void createdNewPost_success_returnStatusOk() throws Exception {
        this.mockMvc
                .perform(
                        post("/unapproved")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))

                )
                .andExpect(status().isOk());
    }

    @Test
    public void createNewPost_success_returnNewTitle() throws Exception{
        this.mockMvc
                .perform(
                        post("/unapproved")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(jsonPath("$.title", is("new test post")));
    }

    @Test
    public void createNewPost_success_returnNewFirstName() throws Exception{
        this.mockMvc
                .perform(
                        post("/unapproved")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(jsonPath("$.firstName", is("new first name")));
    }

    @Test
    public void createNewPost_success_returnNewLastName() throws Exception{
        this.mockMvc
                .perform(
                        post("/unapproved")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(jsonPath("$.lastName", is("new last name")));
    }

    @Test
    public void createNewPost_success_returnNewContactDetails() throws Exception{
        this.mockMvc
                .perform(
                        post("/unapproved")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(jsonPath("$.contactDetails", is("new email")));
    }
    @Test
    public void createNewPost_success_returnNewPostBody() throws Exception{
        this.mockMvc
                .perform(
                        post("/unapproved")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newPost))
                )
                .andExpect(jsonPath("$.postBody", is("new post body")));
    }

    @Test
    public void deletePostById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/all/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePostById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/all/1"));

        verify(mockPostRepository, times(1)).delete(1L);
    }

    @Test
    public void deletePostById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/all/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updatePostById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        put("/all/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondPost))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updatePostById_success_returnsUpdatedApproved() throws Exception {

        this.mockMvc
                .perform(
                        put("/all/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondPost))
                )
                .andExpect(jsonPath("$.approved", is(true)));
    }
    
    @Test
    public void updatePostById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(
                        put("/all/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondPost))
                )
                .andExpect(status().isNotFound());
    }
}

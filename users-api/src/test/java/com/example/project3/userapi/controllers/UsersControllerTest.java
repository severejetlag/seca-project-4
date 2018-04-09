package com.example.project3.userapi.controllers;


import com.example.project3.controllers.UsersController;
import com.example.project3.models.User;
import com.example.project3.repositories.UserRepository;
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
@WebMvcTest(UsersController.class)
public class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @MockBean
    private UserRepository mockUserRepository;
    private User updatedSecondUser;
    private User newUser;

    @Before
    public void setUp() {
        User firstUser = new User(
                "user1",
                "Nick",
                "Lee",
                "password",
                "Bronx",
                "I heart NY"
        );

        User secondUser = new User(
                "user2",
                "Lick",
                "Nee",
                "password2",
                "Queens",
                "NYC is the best"
        );

        updatedSecondUser = new User(
                "new_user2",
                "Kick",
                "Mee",
                "password3",
                "Queens 2",
                "NYC is still the best"
        );

        newUser = new User(
                "new_user3",
                "new",
                "user",
                "password",
                "Brooklyn",
                "I am new to NYC"
        );

        List<User> mockUsers =
                Stream.of(firstUser, secondUser).collect(Collectors.toList());

        given(mockUserRepository.findAll()).willReturn(mockUsers);
        given(mockUserRepository.findOne(1L)).willReturn(firstUser);
        given(mockUserRepository.findByUserName("user1")).willReturn(firstUser);
        given(mockUserRepository.findOne(4L)).willReturn(null);

        // Mock out Delete to return EmptyResultDataAccessException for missing user with ID of 4
        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("oh no!", 1234);
        }).when(mockUserRepository).delete(4L);

        given(mockUserRepository.save(updatedSecondUser)).willReturn(updatedSecondUser);
        given(mockUserRepository.save(newUser)).willReturn((newUser));

    }

    @Test
    public void findAllUsers_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers_success_returnAllUsersAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$", hasSize(2)));
    }
    @Test
    public void findAllUsers_success_returnUserNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$[0].userName", is("user1")));
    }

    @Test
    public void findAllUsers_success_returnFirstNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$[0].firstName", is("Nick")));
    }

    @Test
    public void findAllUsers_success_returnLastNameForEachUser() throws Exception {

        this.mockMvc
                .perform(get("/users"))
                .andExpect(jsonPath("$[0].lastName", is("Lee")));
    }

    @Test
    public void findUserByUserName_success_returnStatusOK() throws Exception {
        this.mockMvc
                .perform(get("/users/search?userName=user1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserByUserName_success_returnUserName() throws Exception {

        this.mockMvc
                .perform(get("/users/search?userName=user1"))
                .andExpect(jsonPath("$.userName", is("user1")));
    }

    @Test
    public void findUserByUserName_success_returnFirstName() throws Exception {

        this.mockMvc
                .perform(get("/users/search?userName=user1"))
                .andExpect(jsonPath("$.firstName", is("Nick")));
    }

    @Test
    public void findUserByUserName_success_returnLastName() throws Exception {

        this.mockMvc
                .perform(get("/users/search?userName=user1"))
                .andExpect(jsonPath("$.lastName", is("Lee")));
    }

    @Test
    public void findUserByUserName_success_returnPassword() throws Exception {

        this.mockMvc
                .perform(get("/users/search?userName=user1"))
                .andExpect(jsonPath("$.password", is("password")));
    }

    @Test
    public void findUserByUserName_success_returnNeighborhood() throws Exception {

        this.mockMvc
                .perform(get("/users/search?userName=user1"))
                .andExpect(jsonPath("$.neighborhood", is("Bronx")));
    }

    @Test
    public void findUserByUserName_success_returnBio() throws Exception {

        this.mockMvc
                .perform(get("/users/search?userName=user1"))
                .andExpect(jsonPath("$.bio", is("I heart NY")));
    }

    @Test
    public void findUserByUserName_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/users/search?userName=user10"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findUserById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findUserById_success_returnUserName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.userName", is("user1")));
    }

    @Test
    public void findUserById_success_returnFirstName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.firstName", is("Nick")));
    }

    @Test
    public void findUserById_success_returnLastName() throws Exception {

        this.mockMvc
                .perform(get("/users/1"))
                .andExpect(jsonPath("$.lastName", is("Lee")));
    }

    @Test
    public void findUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/users/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUserById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/users/1"));

        verify(mockUserRepository, times(1)).delete(1L);
    }

    @Test
    public void deleteUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/users/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserById_success_returnsUpdatedUserName() throws Exception {

        this.mockMvc
                .perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.userName", is("new_user2")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedFirstName() throws Exception {

        this.mockMvc
                .perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.firstName", is("Kick")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedLastName() throws Exception {

        this.mockMvc
                .perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.lastName", is("Mee")));
    }

    @Test
    public void updateUserById_success_returnsUpdatedPassword() throws Exception {
        this.mockMvc
                .perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.password", is("password3")));
    }
    @Test
    public void updatedUserById_success_returnUpdatedNeighborhood() throws Exception {
        this.mockMvc
                .perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(jsonPath("$.neighborhood", is("Queens 2")));
    }

    @Test
    public void updateUserById_success_returnUpdatedBio() throws Exception {
        this.mockMvc
                .perform(
                        put("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString((updatedSecondUser)))
                )
                .andExpect(jsonPath("$.bio", is("NYC is still the best")));
    }

    @Test
    public void updateUserById_failure_userNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(
                        put("/users/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondUser))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void createdNewUser_success_returnStatusOk() throws Exception {
        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))

                )
                .andExpect(status().isOk());
    }

    @Test
    public void createNewUser_success_returnNewUserName() throws Exception{
        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.userName", is("new_user3")));
    }

    @Test
    public void createNewUser_success_returnNewFirstName() throws Exception{
        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.firstName", is("new")));
    }

    @Test
    public void createNewUser_success_returnNewLastName() throws Exception{
        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.lastName", is("user")));
    }

    @Test
    public void createNewUser_success_returnNewPassword() throws Exception{
        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.password", is("password")));
    }
    @Test
    public void createNewUser_success_returnNewNeighborhood() throws Exception{
        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.neighborhood", is("Brooklyn")));
    }

    @Test
    public void createNewUser_success_returnNewBio() throws Exception{
        this.mockMvc
                .perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newUser))
                )
                .andExpect(jsonPath("$.bio", is("I am new to NYC")));
    }
}

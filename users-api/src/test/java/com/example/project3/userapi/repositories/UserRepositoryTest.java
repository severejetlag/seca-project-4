package com.example.project3.userapi.repositories;
import com.example.project3.models.User;
import com.example.project3.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();

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

        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllUsers() {

        List<User> usersFromDb = userRepository.findAll();

        assertThat(usersFromDb.size(), is(2));
    }

    @Test
    public void findAll_returnsUserName() {
        List<User> usersFromDb = userRepository.findAll();
        String secondUsersUserName = usersFromDb.get(1).getUserName();

        assertThat(secondUsersUserName, is("user2"));
    }

    @Test
    public void findAll_returnsFirstName() {
        List<User> usersFromDb = userRepository.findAll();
        String secondUsersFirstName = usersFromDb.get(1).getFirstName();

        assertThat(secondUsersFirstName, is("Lick"));
    }

    @Test
    public void findAll_returnsLastName() {
        List<User> usersFromDb = userRepository.findAll();
        String secondUsersLastName = usersFromDb.get(1).getLastName();

        assertThat(secondUsersLastName, is("Nee"));
    }

    @Test
    public void findByUserName_returnsUserName(){
        User userFromDb = userRepository.findByUserName("user1");
        assertThat(userFromDb.getUserName(), is("user1"));
    }

    @Test
    public void findByUserName_returnsFirstName(){
        User userFromDb = userRepository.findByUserName("user1");
        assertThat(userFromDb.getFirstName(), is("Nick"));
    }

    @Test
    public void findByUserName_returnsLastName(){
        User userFromDb = userRepository.findByUserName("user1");
        assertThat(userFromDb.getLastName(), is("Lee"));
    }
}

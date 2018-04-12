package com.example.postsapi.repositories;
import com.example.postsapi.models.Post;
import com.example.postsapi.repositories.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Before
    public void setUp() {
        postRepository.deleteAll();

        LocalDateTime today = LocalDateTime.now();

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
                true,
                true
                );

        entityManager.persist(firstPost);
        entityManager.persist(secondPost);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllPosts() {

        List<Post> postsFromDb = postRepository.findAll();

        assertThat(postsFromDb.size(), is(2));
    }

    @Test
    public void findAll_returnsPostName() {
        List<Post> postsFromDb = postRepository.findAll();
        String secondPostsPostName = postsFromDb.get(1).getTitle();

        assertThat(secondPostsPostName, is("Local Townhall in Queens"));
    }

    @Test
    public void findAll_returnsFirstName() {
        List<Post> postsFromDb = postRepository.findAll();
        String secondPostsFirstName = postsFromDb.get(1).getFirstName();

        assertThat(secondPostsFirstName, is("Lick"));
    }

    @Test
    public void findAll_returnsLastName() {
        List<Post> postsFromDb = postRepository.findAll();
        String secondPostsLastName = postsFromDb.get(1).getLastName();

        assertThat(secondPostsLastName, is("Nee"));
    }
    
}

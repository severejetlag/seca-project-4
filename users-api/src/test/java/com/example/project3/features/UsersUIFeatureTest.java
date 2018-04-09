package com.example.project3.features;

import com.example.project3.models.User;
import com.example.project3.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersUIFeatureTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudForAUser() throws Exception {
        User firstUser = new User(
                "user1",
                "Nick",
                "Lee",
                "password",
                "Bronx",
                "I heart NY"
        );

        firstUser = userRepository.save(firstUser);

        User secondUser = new User(
                "user2",
                "Lick",
                "Nee",
                "password2",
                "Queens",
                "NYC is the best"
        );

        secondUser = userRepository.save(secondUser);

        System.setProperty("selenide.browser", "Chrome");
        System.setProperty("selenide.headless", "true");

        // Open home page
        open("http://localhost:3000");

        // Login
        WebElement login = $(By.name("userName"));
        login.sendKeys("user1");
        login.submit();

        // Check for test user data
        $("body").shouldHave(text("I heart NY"));
        $("body").shouldHave(text("Queens"));
        $$(".user").shouldHave(size(2));

        // Reload page
        refresh();

        // Login again
        login.sendKeys("user1");
        $("#admin-checkbox").click();
        login.submit();

        // Check for users
        $$(".user").shouldHave(size(2));
        // Check for admin functions and delete one
        $$(".admin-user-delete").shouldHave(size(1));
        $(".admin-user-delete").click();
        $$(".admin-user-delete").shouldHave(size(0));

        // Navigate to profile page and check for elements
        $("a[href='/profile']").click();
        $("#profile-container").should(exist);
        $("#edit-profile-button").should(exist);

        // Enter Edit Mode
        $("#edit-profile-button").click();
        $("#edit-user-form").should(exist);
        $(By.name("userName")).setValue("updateduser1");
        $(By.name("firstName")).setValue("Updated");
        $(By.name("lastName")).setValue("Name").submit();

        // Refresh page to check for persistence
        refresh();
        login.sendKeys("updateduser1");
        login.submit();

        // Check for updated info
        $("body").shouldHave(text("updateduser1"));
        $("body").shouldHave(text("Updated"));
        $("body").shouldHave(text("Name"));

        // Check delete function
        $("a[href='/profile']").click();
        $("#delete-profile-button").should(exist);
        $("#delete-profile-button").click();

        // CHeck if i'm sent back to login
        $(By.name("userName")).should(exist);

        $("a[href='/signup']").click();
        $("#signup-form").should(exist);

        $(By.name("userName")).setValue("newuser");
        $(By.name("firstName")).setValue("Nick");
        $(By.name("lastName")).setValue("Lee");
        $(By.name("password")).setValue("password");
        $(By.name("neighborhood")).setValue("San Francisco");
        $(By.name("bio")).setValue("I am a brand new users moving to NYC").submit();

        $$(".user").shouldHave(size(1));
        $("body").shouldHave(text("newuser"));
        $("body").shouldHave(text("Nick"));
        $("body").shouldHave(text("Lee"));
        $("body").shouldHave(text("San Francisco"));
        $("body").shouldHave(text("I am a brand new users moving to NYC"));

    }
}

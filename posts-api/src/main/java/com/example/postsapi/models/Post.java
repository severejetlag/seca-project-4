package com.example.postsapi.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "POST_BODY")
    private String postBody;

    @Column(name = "DATE_CREATED")
    private LocalDateTime dateCreated;

    @Column(name = "APPROVED")
    private Boolean approved;

    public Post(String title,  String firstName, String lastName, String postBody, LocalDateTime dateCreated, Boolean approved) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postBody = postBody;
        this.dateCreated = dateCreated;
        this.approved = approved; 
    }

    public Post(String title,  String firstName, String lastName, String postBody,  Boolean approved) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postBody = postBody;
        this.approved = approved;
    }
}

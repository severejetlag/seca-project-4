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

    @Column(name = "CONTACT_DETAILS")
    private String contactDetails;

    @Column(name = "POST_BODY")
    private String postBody;



    @Column(name = "APPROVED")
    private Boolean approved;

    @Column(name = "VERIFIED")
    private Boolean verified;

    public Post(String title, String firstName, String lastName, String contactDetails, String postBody) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactDetails = contactDetails;
        this.postBody = postBody;
    }

    public Post(String title,  String firstName, String lastName, String contactDetails, String postBody,  Boolean approved, Boolean verified) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactDetails = contactDetails;
        this.postBody = postBody;
        this.approved = approved;
        this.verified = verified;
    }
}

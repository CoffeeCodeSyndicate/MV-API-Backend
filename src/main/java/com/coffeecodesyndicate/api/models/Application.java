package com.coffeecodesyndicate.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String formTitle;
    private String formBody;

    //many applications can belong to a user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return formTitle; }
    public void setTitle(String formTitle) { this.formTitle = formTitle; }

    public String getBody() { return formBody; }
    public void setBody(String formBody) { this.formBody = formBody; }
}

package com.coffeecodesyndicate.api.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationID;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "application_date")
    private LocalDate applicationDate;


    private String formTitle;
    private String formBody;

    //many applications can belong to a user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //man applications can be linked to one pet
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    //getters & setters
    public Integer getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(Integer applicationID) {
        this.applicationID = applicationID;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFormBody() {
        return formBody;
    }

    public void setFormBody(String formBody) {
        this.formBody = formBody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}

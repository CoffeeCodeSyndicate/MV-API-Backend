package com.coffeecodesyndicate.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String breed;
    private Integer age;
    private String description;
    private Boolean isAdopted;

    //pets can have only one owner
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = Pet.this.description; }

    public Boolean getIsAdopted() { return isAdopted; }
    public void setIsAdopted(Boolean isAdopted) { this.isAdopted = isAdopted; }
}

package com.coffeecodesyndicate.api.config;

import java.time.LocalDate;

import com.coffeecodesyndicate.api.models.Application;
import com.coffeecodesyndicate.api.models.ApplicationStatus;
import com.coffeecodesyndicate.api.models.Pet;
import com.coffeecodesyndicate.api.models.User;
import com.coffeecodesyndicate.api.repositories.ApplicationRepository;
import com.coffeecodesyndicate.api.repositories.PetRepository;
import com.coffeecodesyndicate.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component

public class DataSeeder implements CommandLineRunner {
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    @Autowired
    public DataSeeder(PetRepository petRepository, UserRepository userRepository, ApplicationRepository applicationRepository){
        this.petRepository = petRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void run(String...args) throws Exception {
        User registered = new User();
        registered.setUsername("lukePowell8");
        registered.setPassword("pass123#");
        registered.setEmail("powell8@example.com");
        registered.setIsRegistered(true);
        registered.setIsAdmin(false);

        User admin = new User();
        admin.setUsername("adminUser");
        admin.setPassword("$2a$10$Xb2YjK1");
        admin.setEmail("admin@pets.com");
        admin.setIsRegistered(true);
        admin.setIsAdmin(true);

        User registered2 = new User();
        registered2.setUsername("janeDoe");
        registered2.setPassword("$2a$10$mkdL");
        registered2.setEmail("petLover@example.com");
        registered2.setIsRegistered(true);
        registered2.setIsAdmin(false);

        User registered3 = new User();
        registered3.setUsername("billBrown");
        registered3.setPassword("dogs4Life");
        registered3.setEmail("brownFamily@example.com");
        registered3.setIsRegistered(true);
        registered3.setIsAdmin(false);

        User registered4 = new User();
        registered4.setUsername("paisClanton");
        registered4.setPassword("p$2015");
        registered4.setEmail("Paisley@example.com");
        registered4.setIsRegistered(true);
        registered4.setIsAdmin(false);

        userRepository.save(admin);
        userRepository.save(registered);
        userRepository.save(registered2);
        userRepository.save(registered3);
        userRepository.save(registered4);

        Pet pet1 = new Pet();
        pet1.setName("Buddy");
        pet1.setBreed("Labrador");
        pet1.setAge(3);
        pet1.setDescription("Friendly dog");
        pet1.setIsAdopted(true);
        pet1.setOwner(registered3);

        Pet pet2 = new Pet();
        pet2.setName("Whiskers");
        pet2.setBreed("Tabby");
        pet2.setAge(2);
        pet2.setDescription("Playful cat");
        pet2.setIsAdopted(true);
        pet2.setOwner(registered4);

        Pet pet3 = new Pet();
        pet3.setName("Duke");
        pet3.setBreed("Boxer");
        pet3.setAge(6);
        pet3.setDescription("Gentle giant, gets along with everyone.");
        pet3.setIsAdopted(false);
        pet3.setOwner(null);

        Pet pet4 = new Pet();
        pet4.setName("Oliver");
        pet4.setBreed("Maine Coon");
        pet4.setAge(1);
        pet4.setDescription("Playful kitten, loves climbing and chasing toys.");
        pet4.setIsAdopted(false);
        pet4.setOwner(null);

        Pet pet5 = new Pet();
        pet5.setName("Molly");
        pet5.setBreed("Beagle");
        pet5.setAge(4);
        pet5.setDescription("Energetic and loves walks.");
        pet5.setIsAdopted(true);
        pet5.setOwner(registered2);

        petRepository.save(pet1);
        petRepository.save(pet2);
        petRepository.save(pet3);
        petRepository.save(pet4);
        petRepository.save(pet5);


        Application app1 = new Application();
        app1.setStatus(ApplicationStatus.PENDING); // assuming you have PENDING, APPROVED, REJECTED in your enum
        app1.setApplicationDate(LocalDate.now().minusDays(3));
        app1.setFormTitle("Adoption for Duke");
        app1.setFormBody("I'm looking for a longtime companion for my family.");
        app1.setUser(registered2); // assign to an existing user
        app1.setPet(pet3); // assign to an existing pet

        Application app2 = new Application();
        app2.setStatus(ApplicationStatus.APPROVED);
        app2.setApplicationDate(LocalDate.now().minusDays(10));
        app2.setFormTitle("Adoption for Whiskers");
        app2.setFormBody("I have shown how responsible I was this school year. Mom said I could get a kitty.");
        app2.setUser(registered4);
        app2.setPet(pet2);

        Application app3 = new Application();
        app3.setStatus(ApplicationStatus.REJECTED);
        app3.setApplicationDate(LocalDate.now().minusDays(1));
        app3.setFormTitle("Adoption for Oliver");
        app3.setFormBody("Have always wanted a Maine Coon and have experience with cats.");
        app3.setUser(registered);
        app3.setPet(pet4);

        applicationRepository.save(app1);
        applicationRepository.save(app2);
        applicationRepository.save(app3);






    }


}


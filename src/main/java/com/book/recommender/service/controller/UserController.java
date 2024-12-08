package com.book.recommender.service.controller;

import com.book.recommender.service.model.User;
import com.book.recommender.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/createAccount")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        try {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }

    /*
    @PostMapping("/userLogin")
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println("Received user: " + user.getUsername() + " " + user.getPassword());

        // Search for user in the database
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        System.out.println(foundUser.getUsername()+" "+foundUser.getPassword());

        // Validate user and password
        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok().body("{\"message\":\"Login successful\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\":\"Invalid username or password\"}");
        }
    }*/

    @PostMapping("/userLogin")
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println("Received user: " + user.getUsername() + " " + user.getPassword());

        // Search for user in the database
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());

        if (foundUser.isPresent()) {
            User existingUser = foundUser.get();
            System.out.println(existingUser.getUsername() + " " + existingUser.getPassword());

            // Validate user and password
            if (existingUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok().body("{\"message\":\"Login successful\"}");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"message\":\"Invalid username or password\"}");
    }

    @GetMapping("/fetchAllUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}

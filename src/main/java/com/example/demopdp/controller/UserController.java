package com.example.demopdp.controller;

import com.example.demopdp.controller.model.User;
import com.example.demopdp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = new ArrayList<User>();


            userRepository.findAll().forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userRepository.save(new User(user.getCommon_name(), user.getLegal_name()));
            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User _user = userRepository.findById(id);

        if (_user != null) {
            _user.setId(id);
            _user.setLegal_name(user.getLegal_name());
            _user.setCommon_name(user.getCommon_name());

            userRepository.update(_user);
            return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try {
            int result = userRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("User was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Cannot delete user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteAllUsers() {
        try {
            int numRows = userRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " User(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete users.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

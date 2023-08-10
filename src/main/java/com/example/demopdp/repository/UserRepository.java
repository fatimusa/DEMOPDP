package com.example.demopdp.repository;

import com.example.demopdp.controller.model.User;

import java.util.List;

public interface UserRepository {
    int save(User user);

    int update(User user);

    User findById(Long id);

    int deleteById(int id);

    List<User> findAll();

    int deleteAll();
}

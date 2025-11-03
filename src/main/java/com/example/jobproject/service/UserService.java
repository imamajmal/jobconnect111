package com.example.jobproject.service;

import com.example.jobproject.entity.User;

public interface UserService {
    User registerUser(User user);
    String login(String username, String password);
    User findByEmail(String email);


    
}


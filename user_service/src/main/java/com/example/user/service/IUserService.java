package com.example.user.service;

import com.example.user.dto.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<User> save(User user);

}

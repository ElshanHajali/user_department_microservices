package com.example.user.service;

import com.example.user.dto.User;
import com.example.user.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    // It brings another service object into this service,
    // in my case department.

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> save(User user) {
        if (user != null) {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}

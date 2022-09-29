package com.example.user.controller;

import com.example.user.VO.ResponseTemplateVO;
import com.example.user.dto.User;
import com.example.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        log.info("New user: " + user);
        return userService.save(user);
    }

    @GetMapping("/id/{id}")
    public ResponseTemplateVO getUserWithDepartmentByUserId(@PathVariable long id) {
        ResponseTemplateVO responseTemplateVO = userService.getUserWithDepartmentByUserId(id);
        log.info("Template Object: " + responseTemplateVO);
        return responseTemplateVO;
    }
}

package com.modak.elearning.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    
    private final UserService userService;

    @PostMapping
    @ResponseBody
    public User createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User getLessonByUser(@PathVariable Long id) {
        return userService.getLessonByUser(id);
    }

    @GetMapping
    @ResponseBody
    public List<User> findAllUser() {
        
        return userService.findAllUsers();
    }
}

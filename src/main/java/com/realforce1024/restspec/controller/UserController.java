package com.realforce1024.restspec.controller;

import com.realforce1024.restspec.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 编程燃风 RealForce1024
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public User findByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }

    @GetMapping("/hi")
    public String sayHi(String username) {
        return "hi: " + username;
    }
}

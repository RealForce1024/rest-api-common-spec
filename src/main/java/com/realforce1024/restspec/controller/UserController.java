package com.realforce1024.restspec.controller;

import com.realforce1024.restspec.common.annotation.NoResponseWrapper;
import com.realforce1024.restspec.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @NoResponseWrapper
    @GetMapping("/hi")
    public String sayHi(String username) {
        return "hi: " + username;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new User());
        }
        return list;
    }
}

package com.realforce1024.restspec.controller;

import com.realforce1024.restspec.domain.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author RealForce1024
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    /**
     * 1. 返回string
     *
     * @param username
     * @return
     */
    @GetMapping("/retString")
    public String sayHello(String username) {
        return username;
    }

    /**
     * 2. 返回JavaBean
     */
    @GetMapping("/retBean")
    public User sayHi(String username) {
        User user = new User();
        user.setUsername(username);
        return user;
    }

    /**
     * 3. 接受一个JavaBean参数
     */
    @PostMapping
    public User addUser(@RequestBody User user) {
        return user;
    }
}

package com.realforce1024.restspec.controller;

import com.realforce1024.restspec.common.BizException;
import com.realforce1024.restspec.common.annotation.NoResponseWrapper;
import com.realforce1024.restspec.common.enums.ResultCodeEnum;
import com.realforce1024.restspec.domain.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.random.RandomGenerator;

/**
 * @author 编程燃风 RealForce1024
 */
@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public User findByUsername(@RequestParam String username) {
        // int i = 1 / 0;
        User user = new User();
        user.setUsername(username);
        return user;
    }

    @NoResponseWrapper
    @GetMapping("/hi")
    public String sayHi(String username) {
        // int i = 1 / 0;
        log.info("username: {}", username);
        return "hi: " + username;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new User());
        }
        log.info("list: {}", list);
        return list;
    }

    @SneakyThrows
    @PostMapping
    public User addUser(@RequestBody User user) {
        if (Objects.equals(user.getUsername(), "gdx")) {
            throw new BizException(ResultCodeEnum.NOT_ALLOWED_USERNAME);
        }
        log.info("user: {}", user);
        return user;
    }


}

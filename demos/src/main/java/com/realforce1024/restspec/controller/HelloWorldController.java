package com.realforce1024.restspec.controller;

import com.realforce1024.restspec.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
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
        log.info("username: {}", username);
        return username;
    }

    /**
     * 2. 返回JavaBean
     */
    @GetMapping("/retBean")
    public User sayHi(String username) {
        int i = 1 / 0;
        log.info("username: {}", username);
        User user = new User();
        user.setUsername(username);
        log.info("user: {}", user);
        return user;
    }

    @PostMapping("aa")
    public Map testXX(String id, String name) {
        HashMap put = new HashMap<>();
        put.put("id", id);
        put.put("name", name);
        return put;
    }

    /**
     * 3. 接受一个JavaBean参数
     */
    @PostMapping
    public User addUser(@RequestBody User user) {
        return user;
    }

    @GetMapping("/list/{count}")
    public List<Data> getList(@PathVariable int count) {
        ArrayList<Data> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Data(i, "test" + i, "test" + i));
        }
        return list;
    }

    @lombok.Data
    @AllArgsConstructor
    class Data {
        private Integer id;
        private String content;
        private String title;
    }
}

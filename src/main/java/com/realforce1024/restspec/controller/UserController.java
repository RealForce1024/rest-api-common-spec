package com.realforce1024.restspec.controller;

import com.realforce1024.restspec.common.annotation.NoResponseWrapper;
import com.realforce1024.restspec.common.validator.A;
import com.realforce1024.restspec.domain.User;
import com.realforce1024.restspec.dto.UserDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public User addUser(@Validated(value = A.class) @RequestBody UserDTO userDTO) {
        // Assert.hasLength(user.getUsername(), "用户名不能为空");

        /*if (Objects.equals(user.getUsername(), "gdx")) {
            throw new BizException(ResultCodeEnum.NOT_ALLOWED_USERNAME);
        }
        */
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        log.info("user: {}", user);
        return user;
    }

    @SneakyThrows
    @PostMapping("/query")
    public User queryUser(@Validated @RequestBody UserDTO userDTO) {
        User userDO = new User();
        BeanUtils.copyProperties(userDTO, userDO);
        log.info("接受参数: {}", userDTO);
        return userDO;
    }
}

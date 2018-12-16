package com.megshan.dynamodb.controller;

import com.megshan.dynamodb.domain.User;
import com.megshan.dynamodb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by shantanu on 11/16/18.
 */
@Slf4j
@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/users/{userId}")
    public User getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/users")
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/users/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody Map<String, Object> updateMap) {
        userService.updateUser(userId, updateMap);
    }
}

package com.megshan.dynamodb.service;

import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.megshan.dynamodb.data.UserRepository;
import com.megshan.dynamodb.domain.User;
import com.megshan.dynamodb.exceptions.DuplicateRecordException;
import com.megshan.dynamodb.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by shantanu on 11/16/18.
 */@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String userId) {

        User user = userRepository.findOne(userId);

        if(user == null) {
            log.error("User not found for userId={}", userId);
            throw new NotFoundException("User not found");
        }

        log.info("Successfully received user={} for userId={}", user, userId);
        return user;
    }

    @Override
    public void saveUser(User user) {

        try {
            userRepository.save(user);
        } catch (ConditionalCheckFailedException ccfe) {
            log.error("User already exists, user={}", user);
            throw new DuplicateRecordException("User already exists");
        }

        log.info("Successfully saved user={}", user);
    }

    @Override
    public void updateUser(String userId, Map<String, Object> updateMap) {

        User user = userRepository.findOne(userId);

        user.setUserName(updateMap.get("userName").toString());

        try {
            userRepository.save(user);
        } catch (ConditionalCheckFailedException ccfe) {
            log.error("User already exists, user={}", user);
            throw new DuplicateRecordException("User already exists");
        }

        log.info("Successfully updated user={}", user);
    }
}

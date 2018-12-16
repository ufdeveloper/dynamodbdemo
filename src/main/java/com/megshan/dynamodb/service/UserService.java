package com.megshan.dynamodb.service;

import com.megshan.dynamodb.domain.User;

import java.util.Map;

/**
 * Created by shantanu on 11/16/18.
 */
public interface UserService {

    User getUser(String userId);

    void saveUser(User user);

    void updateUser(String userId, Map<String, Object> updateMap);
}

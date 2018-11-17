package com.megshan.dynamodb.data;

import com.megshan.dynamodb.domain.User;

/**
 * Created by shantanu on 11/17/18.
 */
public interface CustomUserRepository {

    void saveIfNotExists(User user);
}

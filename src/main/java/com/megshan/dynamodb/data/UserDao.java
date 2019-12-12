package com.megshan.dynamodb.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.megshan.dynamodb.domain.User;

/**
 * Created by shantanu on 12/15/18.
 */
public interface UserDao {

    User getUser(String id);

    void saveUser(User user);
}

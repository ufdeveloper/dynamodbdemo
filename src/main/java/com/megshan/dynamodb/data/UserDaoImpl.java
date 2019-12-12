package com.megshan.dynamodb.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.megshan.dynamodb.config.DynamoDBConfig;
import com.megshan.dynamodb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by shantanu on 12/15/18.
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public User getUser(String id) {
        User user = dynamoDBMapper.load(User.class, id);
        return user;
    }

    @Override
    public void saveUser(User user) {
        dynamoDBMapper.save(user, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES));
    }
}

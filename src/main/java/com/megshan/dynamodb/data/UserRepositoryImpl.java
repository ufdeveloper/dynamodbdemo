package com.megshan.dynamodb.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.megshan.dynamodb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shantanu on 11/17/18.
 */
@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public void saveIfNotExists(User user) {

        // Set expected false for an attribute
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue();
        expectedAttributeValue.setExists(Boolean.FALSE);

        // Map the id field to the ExpectedAttributeValue
        Map<String, ExpectedAttributeValue> expectedAttributes = new HashMap<>();
        expectedAttributes.put("userid", expectedAttributeValue);

        // Use the attributes within a DynamoDBSaveExpression
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
        saveExpression.setExpected(expectedAttributes);

        // Save to dynamoDBMapper using the saveExpression
        dynamoDBMapper.save(user, saveExpression);
    }
}

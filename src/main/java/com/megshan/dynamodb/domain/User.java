package com.megshan.dynamodb.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by shantanu on 11/16/18.
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "users")
public class User {

    @DynamoDBHashKey(attributeName = "userid")
    String id;

    @DynamoDBAttribute
    String userName;

    @DynamoDBAttribute
    String firstName;

    @DynamoDBAttribute
    String lastName;

//    @DynamoDBVersionAttribute
//    Long version;
}

package com.example.KodemiLabs.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.KodemiLabs.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void save(User user) {
        dynamoDBMapper.save(user);
    }

    public User getUserById(String userId) {
        return dynamoDBMapper.load(User.class, userId);
    }

    public User getUserByEmail(String email) {

        Map<String, AttributeValue> values = new HashMap<>();
        values.put(":email", new AttributeValue().withS(email));

        DynamoDBQueryExpression<User> query =
                new DynamoDBQueryExpression<User>()
                        .withIndexName("email-index")
                        .withConsistentRead(false)
                        .withKeyConditionExpression("email = :email")
                        .withExpressionAttributeValues(values);

        List<User> users = dynamoDBMapper.query(User.class, query);
        return users.isEmpty() ? null : users.get(0);
    }
}

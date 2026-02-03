package com.example.KodemiLabs.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.KodemiLabs.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    public User getUserId(String email) {
        return dynamoDBMapper.load(User.class, email);
    }
}

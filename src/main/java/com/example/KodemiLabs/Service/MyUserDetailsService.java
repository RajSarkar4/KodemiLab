package com.example.KodemiLabs.Service;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.KodemiLabs.Model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final DynamoDBMapper dynamoDBMapper;

    public MyUserDetailsService(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":username", new AttributeValue().withS(username));

        DynamoDBQueryExpression<User> queryExpression =
                new DynamoDBQueryExpression<User>()
                        .withIndexName("username-index")
                        .withConsistentRead(false)
                        .withKeyConditionExpression("username = :username")
                        .withExpressionAttributeValues(eav)
                        .withLimit(1);

        List<User> users =
                dynamoDBMapper.query(User.class, queryExpression);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = users.get(0);

        String role = user.getRole() != null
                ? user.getRole().name()
                : "USER";

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPasswordHash())
                .roles(role)
                .build();
    }
}
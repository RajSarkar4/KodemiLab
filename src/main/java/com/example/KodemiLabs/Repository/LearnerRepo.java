package com.example.KodemiLabs.Repository;

import com.example.KodemiLabs.Model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface LearnerRepo extends CrudRepository<User, String> {

    List<User> findByName(String name);

}
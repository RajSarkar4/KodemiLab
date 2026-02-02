package com.example.KodemiLabs.Repository;

import com.example.KodemiLabs.Model.Learner;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface LearnerRepo extends CrudRepository<Learner, String> {

    List<Learner> findByName(String name);

}
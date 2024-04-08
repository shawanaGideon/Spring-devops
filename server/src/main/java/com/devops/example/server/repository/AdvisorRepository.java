package com.devops.example.server.repository;

import com.devops.example.server.model.Advisor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends ReactiveMongoRepository<Advisor, String> {
}

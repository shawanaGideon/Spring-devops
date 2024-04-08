package com.devops.example.server.repository;

import com.devops.example.server.model.Security;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SecurityRepository extends ReactiveMongoRepository<Security, String> {
}

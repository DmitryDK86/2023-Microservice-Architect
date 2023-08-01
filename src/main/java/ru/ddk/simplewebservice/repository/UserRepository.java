package ru.ddk.simplewebservice.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.ddk.simplewebservice.domain.User;

public interface UserRepository extends Neo4jRepository<User, String> {
}

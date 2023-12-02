package ru.ddk.simplewebservice.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.ddk.simplewebservice.domain.TranManager;

public interface TranManagerRepository  extends Neo4jRepository<TranManager, String> {
}

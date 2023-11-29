package ru.ddk.simplewebservice.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ru.ddk.simplewebservice.domain.LocalChanges;

public interface LocalChangesRepository extends Neo4jRepository<LocalChanges, String> {
}

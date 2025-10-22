package com.paklog.sustainability.domain.repository;

import com.paklog.sustainability.domain.aggregate.GreenInitiative;
import com.paklog.sustainability.domain.valueobject.InitiativeStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GreenInitiativeRepository extends MongoRepository<GreenInitiative, String> {
    List<GreenInitiative> findByStatus(InitiativeStatus status);
}

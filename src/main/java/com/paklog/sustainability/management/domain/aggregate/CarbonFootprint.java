package com.paklog.sustainability.management.domain.aggregate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * CarbonFootprint Aggregate Root
 */
@Document(collection = "carbonfootprints")
public class CarbonFootprint {

    @Id
    private String id;

    private Instant createdAt;
    private Instant updatedAt;

    // Domain logic methods here


    // Getters
    public String getId() { return id; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
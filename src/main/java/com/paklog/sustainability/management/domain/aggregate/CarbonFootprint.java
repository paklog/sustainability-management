package com.paklog.sustainability.management.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * CarbonFootprint Aggregate Root
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "carbonfootprints")
public class CarbonFootprint {

    @Id
    private String id;

    private Instant createdAt;
    private Instant updatedAt;

    // Domain logic methods here
}
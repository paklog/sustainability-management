package com.paklog.sustainability.domain.aggregate;

import com.paklog.sustainability.domain.valueobject.EmissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "emission_sources")
public class EmissionSource {
    @Id
    private String sourceId;
    private String name;
    private EmissionType type;
    private String description;
    private double emissionFactorCO2ePerUnit;
    private String unit;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;

    public double calculateEmissions(double quantity) {
        return quantity * emissionFactorCO2ePerUnit;
    }
}

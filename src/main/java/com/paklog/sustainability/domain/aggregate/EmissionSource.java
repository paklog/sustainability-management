package com.paklog.sustainability.domain.aggregate;

import com.paklog.sustainability.domain.valueobject.EmissionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

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


    // Getters
    public String getSourceId() { return sourceId; }
    public String getName() { return name; }
    public EmissionType getType() { return type; }
    public String getDescription() { return description; }
    public double getEmissionFactorCO2ePerUnit() { return emissionFactorCO2ePerUnit; }
    public String getUnit() { return unit; }
    public boolean isActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // Setters
    public void setSourceId(String sourceId) { this.sourceId = sourceId; }
    public void setName(String name) { this.name = name; }
    public void setType(EmissionType type) { this.type = type; }
    public void setDescription(String description) { this.description = description; }
    public void setEmissionFactorCO2ePerUnit(double emissionFactorCO2ePerUnit) { this.emissionFactorCO2ePerUnit = emissionFactorCO2ePerUnit; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setActive(boolean active) { this.active = active; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}

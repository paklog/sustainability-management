package com.paklog.sustainability.domain.aggregate;

import com.paklog.sustainability.domain.valueobject.EmissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "carbon_footprints")
public class CarbonFootprint {
    @Id
    private String footprintId;
    private String warehouseId;
    private LocalDate recordDate;
    private EmissionType emissionType;
    
    @Builder.Default
    private Map<String, Double> emissionsBySource = new HashMap<>();
    
    private double totalCO2eKg;
    private String calculationMethod;
    private String notes;

    public void addEmission(String source, double co2eKg) {
        emissionsBySource.put(source, co2eKg);
        recalculateTotal();
    }

    private void recalculateTotal() {
        this.totalCO2eKg = emissionsBySource.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double getTotalCO2eTons() {
        return totalCO2eKg / 1000.0;
    }
}

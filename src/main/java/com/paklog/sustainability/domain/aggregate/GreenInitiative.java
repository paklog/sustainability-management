package com.paklog.sustainability.domain.aggregate;

import com.paklog.sustainability.domain.valueobject.InitiativeStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "green_initiatives")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GreenInitiative {
    @Id
    private String initiativeId;
    private String name;
    private String description;
    private InitiativeStatus status;
    private LocalDate startDate;
    private LocalDate targetCompletionDate;
    private LocalDate actualCompletionDate;
    private double targetReductionCO2eKg;
    private double actualReductionCO2eKg;
    private double estimatedCost;
    private double actualCost;
    private String owner;

    public double getAchievementPercentage() {
        if (targetReductionCO2eKg <= 0) return 0.0;
        return (actualReductionCO2eKg / targetReductionCO2eKg) * 100.0;
    }

    public double getROI() {
        if (actualCost <= 0) return 0.0;
        double savings = actualReductionCO2eKg * 50; // $50 per ton CO2e
        return (savings - actualCost) / actualCost * 100.0;
    }
}

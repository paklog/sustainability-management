package com.paklog.sustainability.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGreenInitiativeCommand {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate targetCompletionDate;
    private double targetReductionCO2eKg;
    private double estimatedCost;
    private String owner;
}

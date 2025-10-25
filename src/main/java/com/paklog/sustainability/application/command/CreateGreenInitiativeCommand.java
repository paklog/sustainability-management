package com.paklog.sustainability.application.command;

import java.time.LocalDate;

public record CreateGreenInitiativeCommand(
    String name,
    String description,
    LocalDate startDate,
    LocalDate targetCompletionDate,
    double targetReductionCO2eKg,
    double estimatedCost,
    String owner
) {}

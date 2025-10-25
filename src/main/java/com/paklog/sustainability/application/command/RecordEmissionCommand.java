package com.paklog.sustainability.application.command;

import com.paklog.sustainability.domain.valueobject.EmissionType;
import java.time.LocalDate;
import java.util.Map;

public record RecordEmissionCommand(
    String warehouseId,
    LocalDate recordDate,
    EmissionType emissionType,
    Map<String, Double> emissionsBySource,
    String notes
) {}

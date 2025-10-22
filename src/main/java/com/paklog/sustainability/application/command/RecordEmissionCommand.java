package com.paklog.sustainability.application.command;

import com.paklog.sustainability.domain.valueobject.EmissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordEmissionCommand {
    private String warehouseId;
    private LocalDate recordDate;
    private EmissionType emissionType;
    private Map<String, Double> emissionsBySource;
    private String notes;
}

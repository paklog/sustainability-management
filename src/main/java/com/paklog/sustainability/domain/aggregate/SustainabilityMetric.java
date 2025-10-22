package com.paklog.sustainability.domain.aggregate;

import com.paklog.sustainability.domain.valueobject.MetricUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "sustainability_metrics")
public class SustainabilityMetric {
    @Id
    private String metricId;
    private String warehouseId;
    private String metricName;
    private MetricUnit unit;
    private double value;
    private LocalDate recordDate;
    private String category;
    private String source;
    private String notes;

    public double normalizeToKgCO2e(double conversionFactor) {
        return value * conversionFactor;
    }
}

package com.paklog.sustainability.domain.aggregate;

import com.paklog.sustainability.domain.valueobject.MetricUnit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

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


    // Getters
    public String getMetricId() { return metricId; }
    public String getWarehouseId() { return warehouseId; }
    public String getMetricName() { return metricName; }
    public MetricUnit getUnit() { return unit; }
    public double getValue() { return value; }
    public LocalDate getRecordDate() { return recordDate; }
    public String getCategory() { return category; }
    public String getSource() { return source; }
    public String getNotes() { return notes; }

    // Setters
    public void setMetricId(String metricId) { this.metricId = metricId; }
    public void setWarehouseId(String warehouseId) { this.warehouseId = warehouseId; }
    public void setMetricName(String metricName) { this.metricName = metricName; }
    public void setUnit(MetricUnit unit) { this.unit = unit; }
    public void setValue(double value) { this.value = value; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
    public void setCategory(String category) { this.category = category; }
    public void setSource(String source) { this.source = source; }
    public void setNotes(String notes) { this.notes = notes; }
}

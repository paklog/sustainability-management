package com.paklog.sustainability.domain.aggregate;

import com.paklog.sustainability.domain.valueobject.ReportingPeriod;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "esg_reports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ESGReport {
 @Id
 private String reportId;
 private String warehouseId;
 private ReportingPeriod period;
 private YearMonth reportMonth;
 private int year;
 private LocalDate generatedAt;

 // Environmental
 private double totalCO2eScope1Kg;
 private double totalCO2eScope2Kg;
 private double totalCO2eScope3Kg;
 private double totalEnergyKwh;
 private double renewableEnergyPercentage;
 private double waterUsageCubicMeters;
 private double wasteGeneratedTons;
 private double wasteRecycledPercentage;

 // Social
 private int workforceCount;
 private double workplaceSafetyScore;
 private int trainingHoursPerEmployee;

 // Governance
 private boolean complianceCertified;
 private int auditsPassed;

 @Builder.Default
 private Map<String, Object> additionalMetrics = new HashMap<>();

 public double getTotalCO2eKg() {
 return totalCO2eScope1Kg + totalCO2eScope2Kg + totalCO2eScope3Kg;
 }

 public double getTotalCO2eTons() {
 return getTotalCO2eKg() / 1000.0;
 }
}

package com.paklog.sustainability.domain.service;

import com.paklog.sustainability.domain.aggregate.CarbonFootprint;
import com.paklog.sustainability.domain.aggregate.ESGReport;
import com.paklog.sustainability.domain.aggregate.SustainabilityMetric;
import com.paklog.sustainability.domain.valueobject.EmissionType;
import com.paklog.sustainability.domain.valueobject.ReportingPeriod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ESGReportingService {

    public ESGReport generateReport(String warehouseId, 
                                    YearMonth reportMonth, 
                                    ReportingPeriod period,
                                    List<CarbonFootprint> footprints,
                                    List<SustainabilityMetric> metrics) {
        
        log.info("Generating ESG report for warehouse {} for {}", warehouseId, reportMonth);
        
        // Calculate scope emissions
        double scope1 = footprints.stream()
                .filter(f -> f.getEmissionType() == EmissionType.SCOPE1_DIRECT)
                .mapToDouble(CarbonFootprint::getTotalCO2eKg)
                .sum();
        
        double scope2 = footprints.stream()
                .filter(f -> f.getEmissionType() == EmissionType.SCOPE2_ENERGY)
                .mapToDouble(CarbonFootprint::getTotalCO2eKg)
                .sum();
        
        double scope3 = footprints.stream()
                .filter(f -> f.getEmissionType() == EmissionType.SCOPE3_TRANSPORTATION || 
                             f.getEmissionType() == EmissionType.SCOPE3_SUPPLY_CHAIN)
                .mapToDouble(CarbonFootprint::getTotalCO2eKg)
                .sum();
        
        // Calculate energy metrics
        double totalEnergy = metrics.stream()
                .filter(m -> m.getCategory().equals("ENERGY"))
                .mapToDouble(SustainabilityMetric::getValue)
                .sum();
        
        double renewableEnergy = metrics.stream()
                .filter(m -> m.getCategory().equals("RENEWABLE_ENERGY"))
                .mapToDouble(SustainabilityMetric::getValue)
                .sum();
        
        double renewablePercentage = totalEnergy > 0 ? (renewableEnergy / totalEnergy) * 100 : 0;
        
        // Water and waste
        double waterUsage = metrics.stream()
                .filter(m -> m.getCategory().equals("WATER"))
                .mapToDouble(SustainabilityMetric::getValue)
                .sum();
        
        double wasteGenerated = metrics.stream()
                .filter(m -> m.getCategory().equals("WASTE_TOTAL"))
                .mapToDouble(SustainabilityMetric::getValue)
                .sum();
        
        double wasteRecycled = metrics.stream()
                .filter(m -> m.getCategory().equals("WASTE_RECYCLED"))
                .mapToDouble(SustainabilityMetric::getValue)
                .sum();
        
        double recyclingRate = wasteGenerated > 0 ? (wasteRecycled / wasteGenerated) * 100 : 0;
        
        return ESGReport.builder()
                .reportId(UUID.randomUUID().toString())
                .warehouseId(warehouseId)
                .period(period)
                .reportMonth(reportMonth)
                .year(reportMonth.getYear())
                .generatedAt(LocalDate.now())
                .totalCO2eScope1Kg(scope1)
                .totalCO2eScope2Kg(scope2)
                .totalCO2eScope3Kg(scope3)
                .totalEnergyKwh(totalEnergy)
                .renewableEnergyPercentage(renewablePercentage)
                .waterUsageCubicMeters(waterUsage)
                .wasteGeneratedTons(wasteGenerated)
                .wasteRecycledPercentage(recyclingRate)
                .complianceCertified(true)
                .build();
    }

    public double calculateCarbonIntensity(ESGReport report, int ordersProcessed) {
        if (ordersProcessed <= 0) return 0.0;
        return report.getTotalCO2eKg() / ordersProcessed;
    }

    public String assessPerformance(ESGReport report) {
        double totalTons = report.getTotalCO2eTons();
        
        if (totalTons < 50) return "EXCELLENT";
        if (totalTons < 100) return "GOOD";
        if (totalTons < 200) return "AVERAGE";
        return "NEEDS_IMPROVEMENT";
    }
}

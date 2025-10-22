package com.paklog.sustainability.domain.service;

import com.paklog.sustainability.domain.aggregate.EmissionSource;
import com.paklog.sustainability.domain.valueobject.EmissionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for calculating CO2 emissions based on activity data
 */
@Service
@Slf4j
public class EmissionCalculationService {

    @Value("${sustainability.carbon.diesel-factor-kg-co2e-per-liter:2.68}")
    private double dieselFactor;

    @Value("${sustainability.carbon.gasoline-factor-kg-co2e-per-liter:2.31}")
    private double gasolineFactor;

    @Value("${sustainability.carbon.electricity-factor-kg-co2e-per-kwh:0.92}")
    private double electricityFactor;

    public double calculateFuelEmissions(String fuelType, double liters) {
        double factor = switch (fuelType.toUpperCase()) {
            case "DIESEL" -> dieselFactor;
            case "GASOLINE", "PETROL" -> gasolineFactor;
            case "LPG" -> 1.51;
            default -> 2.5; // Default average
        };
        
        double emissions = liters * factor;
        log.debug("Fuel emissions: {} liters of {} = {} kg CO2e", liters, fuelType, emissions);
        return emissions;
    }

    public double calculateElectricityEmissions(double kwh) {
        double emissions = kwh * electricityFactor;
        log.debug("Electricity emissions: {} kWh = {} kg CO2e", kwh, emissions);
        return emissions;
    }

    public double calculateTransportationEmissions(double distanceKm, String vehicleType, double loadTons) {
        // Emission factors per ton-km
        double factor = switch (vehicleType.toUpperCase()) {
            case "TRUCK_SMALL" -> 0.096;  // kg CO2e per ton-km
            case "TRUCK_MEDIUM" -> 0.062;
            case "TRUCK_LARGE" -> 0.049;
            case "RAIL" -> 0.022;
            case "AIR" -> 0.602;
            case "SEA" -> 0.011;
            default -> 0.062;
        };
        
        double emissions = distanceKm * loadTons * factor;
        log.debug("Transportation emissions: {} km, {} tons via {} = {} kg CO2e", 
                 distanceKm, loadTons, vehicleType, emissions);
        return emissions;
    }

    public double calculatePackagingEmissions(String materialType, double weightKg) {
        // Emission factors per kg of material
        double factor = switch (materialType.toUpperCase()) {
            case "CARDBOARD" -> 0.85;
            case "PLASTIC" -> 3.5;
            case "WOOD" -> 0.42;
            case "METAL" -> 2.1;
            default -> 1.5;
        };
        
        return weightKg * factor;
    }

    public EmissionType determineScope(String activityType) {
        return switch (activityType.toUpperCase()) {
            case "FORKLIFT_FUEL", "COMPANY_VEHICLE", "GENERATOR" -> EmissionType.SCOPE1_DIRECT;
            case "ELECTRICITY", "HEATING", "COOLING" -> EmissionType.SCOPE2_ENERGY;
            case "TRANSPORTATION", "SHIPPING", "LOGISTICS" -> EmissionType.SCOPE3_TRANSPORTATION;
            case "PACKAGING", "SUPPLIES", "WASTE" -> EmissionType.SCOPE3_SUPPLY_CHAIN;
            default -> EmissionType.SCOPE3_SUPPLY_CHAIN;
        };
    }
}

package com.paklog.sustainability.infrastructure.rest;

import com.paklog.sustainability.application.command.CreateGreenInitiativeCommand;
import com.paklog.sustainability.application.command.RecordEmissionCommand;
import com.paklog.sustainability.application.service.SustainabilityApplicationService;
import com.paklog.sustainability.domain.aggregate.CarbonFootprint;
import com.paklog.sustainability.domain.aggregate.ESGReport;
import com.paklog.sustainability.domain.aggregate.GreenInitiative;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sustainability")
@Tag(name = "Sustainability Management", description = "ESG and carbon footprint tracking APIs")
public class SustainabilityController {

    private final SustainabilityApplicationService applicationService;
    public SustainabilityController(SustainabilityApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @PostMapping("/emissions")
    @Operation(summary = "Record carbon emissions")
    public ResponseEntity<CarbonFootprint> recordEmission(@RequestBody RecordEmissionCommand command) {
        CarbonFootprint footprint = applicationService.recordEmission(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(footprint);
    }

    @GetMapping("/emissions")
    @Operation(summary = "Get emissions for date range")
    public ResponseEntity<List<CarbonFootprint>> getEmissions(
            @RequestParam String warehouseId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<CarbonFootprint> footprints = applicationService.getFootprints(warehouseId, startDate, endDate);
        return ResponseEntity.ok(footprints);
    }

    @PostMapping("/initiatives")
    @Operation(summary = "Create green initiative")
    public ResponseEntity<GreenInitiative> createInitiative(@RequestBody CreateGreenInitiativeCommand command) {
        GreenInitiative initiative = applicationService.createInitiative(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(initiative);
    }

    @GetMapping("/initiatives/active")
    @Operation(summary = "Get active initiatives")
    public ResponseEntity<List<GreenInitiative>> getActiveInitiatives() {
        List<GreenInitiative> initiatives = applicationService.getActiveInitiatives();
        return ResponseEntity.ok(initiatives);
    }

    @PostMapping("/reports/generate")
    @Operation(summary = "Generate ESG report")
    public ResponseEntity<ESGReport> generateReport(
            @RequestParam String warehouseId,
            @RequestParam String yearMonth) {
        YearMonth month = YearMonth.parse(yearMonth);
        ESGReport report = applicationService.generateMonthlyReport(warehouseId, month);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }
}

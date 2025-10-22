package com.paklog.sustainability.application.service;

import com.paklog.sustainability.application.command.CreateGreenInitiativeCommand;
import com.paklog.sustainability.application.command.RecordEmissionCommand;
import com.paklog.sustainability.domain.aggregate.CarbonFootprint;
import com.paklog.sustainability.domain.aggregate.ESGReport;
import com.paklog.sustainability.domain.aggregate.GreenInitiative;
import com.paklog.sustainability.domain.aggregate.SustainabilityMetric;
import com.paklog.sustainability.domain.repository.CarbonFootprintRepository;
import com.paklog.sustainability.domain.repository.ESGReportRepository;
import com.paklog.sustainability.domain.repository.GreenInitiativeRepository;
import com.paklog.sustainability.domain.service.ESGReportingService;
import com.paklog.sustainability.domain.valueobject.InitiativeStatus;
import com.paklog.sustainability.domain.valueobject.ReportingPeriod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SustainabilityApplicationService {

    private final CarbonFootprintRepository footprintRepository;
    private final ESGReportRepository reportRepository;
    private final GreenInitiativeRepository initiativeRepository;
    private final ESGReportingService reportingService;

    @Transactional
    public CarbonFootprint recordEmission(RecordEmissionCommand command) {
        CarbonFootprint footprint = CarbonFootprint.builder()
                .footprintId(UUID.randomUUID().toString())
                .warehouseId(command.getWarehouseId())
                .recordDate(command.getRecordDate())
                .emissionType(command.getEmissionType())
                .emissionsBySource(command.getEmissionsBySource())
                .notes(command.getNotes())
                .build();
        
        footprint.emissionsBySource.forEach(footprint::addEmission);
        
        return footprintRepository.save(footprint);
    }

    @Transactional
    public GreenInitiative createInitiative(CreateGreenInitiativeCommand command) {
        GreenInitiative initiative = GreenInitiative.builder()
                .initiativeId(UUID.randomUUID().toString())
                .name(command.getName())
                .description(command.getDescription())
                .status(InitiativeStatus.PLANNED)
                .startDate(command.getStartDate())
                .targetCompletionDate(command.getTargetCompletionDate())
                .targetReductionCO2eKg(command.getTargetReductionCO2eKg())
                .estimatedCost(command.getEstimatedCost())
                .owner(command.getOwner())
                .actualReductionCO2eKg(0.0)
                .actualCost(0.0)
                .build();
        
        return initiativeRepository.save(initiative);
    }

    @Transactional
    public ESGReport generateMonthlyReport(String warehouseId, YearMonth month) {
        LocalDate startDate = month.atDay(1);
        LocalDate endDate = month.atEndOfMonth();
        
        List<CarbonFootprint> footprints = footprintRepository
                .findByWarehouseIdAndRecordDateBetween(warehouseId, startDate, endDate);
        
        ESGReport report = reportingService.generateReport(
                warehouseId, month, ReportingPeriod.MONTHLY, footprints, List.of());
        
        return reportRepository.save(report);
    }

    @Transactional(readOnly = true)
    public List<CarbonFootprint> getFootprints(String warehouseId, LocalDate startDate, LocalDate endDate) {
        return footprintRepository.findByWarehouseIdAndRecordDateBetween(warehouseId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<GreenInitiative> getActiveInitiatives() {
        return initiativeRepository.findByStatus(InitiativeStatus.IN_PROGRESS);
    }
}

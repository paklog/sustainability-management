package com.paklog.sustainability.application.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
public class SustainabilityApplicationService {
    private static final Logger log = LoggerFactory.getLogger(SustainabilityApplicationService.class);


    private final CarbonFootprintRepository footprintRepository;
    private final ESGReportRepository reportRepository;
    private final GreenInitiativeRepository initiativeRepository;
    private final ESGReportingService reportingService;
    public SustainabilityApplicationService(CarbonFootprintRepository footprintRepository, ESGReportRepository reportRepository, GreenInitiativeRepository initiativeRepository, ESGReportingService reportingService) {
        this.footprintRepository = footprintRepository;
        this.reportRepository = reportRepository;
        this.initiativeRepository = initiativeRepository;
        this.reportingService = reportingService;
    }


    @Transactional
    public CarbonFootprint recordEmission(RecordEmissionCommand command) {
        CarbonFootprint footprint = CarbonFootprint.builder()
                .footprintId(UUID.randomUUID().toString())
                .warehouseId(command.warehouseId())
                .recordDate(command.recordDate())
                .emissionType(command.emissionType())
                .notes(command.notes())
                .build();

        command.emissionsBySource().forEach(footprint::addEmission);

        return footprintRepository.save(footprint);
    }

    @Transactional
    public GreenInitiative createInitiative(CreateGreenInitiativeCommand command) {
        GreenInitiative initiative = GreenInitiative.builder()
                .initiativeId(UUID.randomUUID().toString())
                .name(command.name())
                .description(command.description())
                .status(InitiativeStatus.PLANNED)
                .startDate(command.startDate())
                .targetCompletionDate(command.targetCompletionDate())
                .targetReductionCO2eKg(command.targetReductionCO2eKg())
                .estimatedCost(command.estimatedCost())
                .owner(command.owner())
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

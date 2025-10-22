package com.paklog.sustainability.domain.repository;

import com.paklog.sustainability.domain.aggregate.ESGReport;
import com.paklog.sustainability.domain.valueobject.ReportingPeriod;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface ESGReportRepository extends MongoRepository<ESGReport, String> {
    Optional<ESGReport> findByWarehouseIdAndReportMonthAndPeriod(
            String warehouseId, YearMonth reportMonth, ReportingPeriod period);
    
    List<ESGReport> findByWarehouseIdAndYear(String warehouseId, int year);
}

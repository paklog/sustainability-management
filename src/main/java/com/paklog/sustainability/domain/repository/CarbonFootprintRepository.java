package com.paklog.sustainability.domain.repository;

import com.paklog.sustainability.domain.aggregate.CarbonFootprint;
import com.paklog.sustainability.domain.valueobject.EmissionType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarbonFootprintRepository extends MongoRepository<CarbonFootprint, String> {
    List<CarbonFootprint> findByWarehouseIdAndRecordDateBetween(
            String warehouseId, LocalDate startDate, LocalDate endDate);
    
    List<CarbonFootprint> findByEmissionType(EmissionType type);
}

package com.era.smart_iot_manager.repository;

import com.era.smart_iot_manager.domain.entities.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> {
    Page<Measurement> findByDeviceId(String deviceId, Pageable pageable);
}


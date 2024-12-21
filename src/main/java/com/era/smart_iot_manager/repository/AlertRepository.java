package com.era.smart_iot_manager.repository;


import com.era.smart_iot_manager.domain.entities.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {
}

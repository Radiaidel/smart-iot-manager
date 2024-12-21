package com.era.smart_iot_manager.repository;


import com.era.smart_iot_manager.domain.entities.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
}

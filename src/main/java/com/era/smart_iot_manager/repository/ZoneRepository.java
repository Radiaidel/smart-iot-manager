package com.era.smart_iot_manager.repository;

import com.era.smart_iot_manager.model.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends MongoRepository<Zone, String> {
}

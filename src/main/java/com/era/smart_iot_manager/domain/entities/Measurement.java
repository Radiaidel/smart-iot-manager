package com.era.smart_iot_manager.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "measurements")
public class Measurement {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private Double value;

    @DBRef
    private Device device;
}


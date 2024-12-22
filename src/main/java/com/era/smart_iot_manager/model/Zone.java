package com.era.smart_iot_manager.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "zones")
public class Zone {
    @Id
    private String id;
    private String name;
    private String type;
    private String location;
}


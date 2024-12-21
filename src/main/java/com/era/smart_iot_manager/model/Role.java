package com.era.smart_iot_manager.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private String name;
    private Integer level;
}
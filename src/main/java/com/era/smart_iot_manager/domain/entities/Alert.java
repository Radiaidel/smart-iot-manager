package com.era.smart_iot_manager.domain.entities;


import com.era.smart_iot_manager.domain.Enum.AlertSeverity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "alerts")
public class Alert {
    @Id
    private String id;
    private AlertSeverity severity;
    private String message;
    private LocalDateTime timestamp;
    @DBRef
    private Device device;

}

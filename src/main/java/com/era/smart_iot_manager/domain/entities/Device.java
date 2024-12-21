package com.era.smart_iot_manager.domain.entities;

import com.era.smart_iot_manager.domain.Enum.DeviceStatus;
import com.era.smart_iot_manager.domain.Enum.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "devices")
public class Device {
    @Id
    private String id;
    private String name;
    private DeviceType type;
    private DeviceStatus status;
    private LocalDateTime lastCommunication;
    private String zoneId;
    @DBRef
    private List<Alert> device;
}
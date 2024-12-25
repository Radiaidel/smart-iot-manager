package com.era.smart_iot_manager.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MeasurementResponse {
    private String id;
    private LocalDateTime timestamp;
    private Double value;
    private String deviceId;
    private String deviceName;
    private String deviceType;
}



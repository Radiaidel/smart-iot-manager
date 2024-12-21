package com.era.smart_iot_manager.dto.response;

import com.era.smart_iot_manager.domain.Enum.AlertSeverity;

import java.time.LocalDateTime;

public class AlertResponse {
    private String id;
    private AlertSeverity severity;
    private String message;
    private LocalDateTime timestamp;
    private String deviceId;
}

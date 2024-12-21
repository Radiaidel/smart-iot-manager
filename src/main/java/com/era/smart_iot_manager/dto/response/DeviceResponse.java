package com.era.smart_iot_manager.dto.response;

import com.era.smart_iot_manager.domain.Enum.DeviceStatus;
import com.era.smart_iot_manager.domain.Enum.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceResponse {
    private String id;
    private String name;
    private DeviceType type;
    private DeviceStatus status;
    private LocalDateTime lastCommunication;
    private String zoneId;
}


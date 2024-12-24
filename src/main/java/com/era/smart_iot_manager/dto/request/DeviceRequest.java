package com.era.smart_iot_manager.dto.request;


import com.era.smart_iot_manager.domain.Enum.DeviceStatus;
import com.era.smart_iot_manager.domain.Enum.DeviceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    private DeviceType type;

    @NotNull(message = "Status is required")
    private DeviceStatus status;

    @NotBlank(message = "Zone ID is required")
    private String zoneId;
}

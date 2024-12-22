package com.era.smart_iot_manager.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ZoneRequest {
    @NotBlank(message = "Zone name is required")
    @Size(max = 100, message = "Zone name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Zone type is required")
    @Size(max = 50, message = "Zone type must not exceed 50 characters")
    private String type;

    @NotBlank(message = "Zone location is required")
    @Size(max = 200, message = "Zone location must not exceed 200 characters")
    private String location;
}
package com.era.smart_iot_manager.mapper;


import com.era.smart_iot_manager.domain.entities.Alert;
import com.era.smart_iot_manager.dto.response.AlertResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlertMapper {
    @Mapping(target = "deviceId", source = "device.id")
    AlertResponse toResponseDto(Alert alert);
}

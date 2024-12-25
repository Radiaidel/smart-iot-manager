package com.era.smart_iot_manager.mapper;


import com.era.smart_iot_manager.domain.entities.Alert;
import com.era.smart_iot_manager.dto.response.AlertResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlertMapper {
    AlertResponse toResponseDto(Alert alert);
}

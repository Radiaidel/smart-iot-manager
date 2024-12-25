package com.era.smart_iot_manager.mapper;

import com.era.smart_iot_manager.domain.entities.Measurement;
import com.era.smart_iot_manager.dto.request.MeasurementRequest;
import com.era.smart_iot_manager.dto.response.MeasurementResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "device", ignore = true)
    Measurement toEntity(MeasurementRequest measurementRequestDto);

    @Mapping(target = "deviceId", source = "device.id")
    @Mapping(target = "deviceName", source = "device.name")
    @Mapping(target = "deviceType", source = "device.type")
    MeasurementResponse toResponseDto(Measurement measurement);
}
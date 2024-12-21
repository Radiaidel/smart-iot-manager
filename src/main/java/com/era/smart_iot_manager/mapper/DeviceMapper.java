package com.era.smart_iot_manager.mapper;


import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.dto.request.DeviceRequest;
import com.era.smart_iot_manager.dto.response.DeviceResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    @Mapping(target = "zoneId", source = "zone.id")
    DeviceResponse toResponseDto(Device device);

    @Mapping(target = "zone.id", source = "zoneId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastCommunication", ignore = true)
    Device toEntity(DeviceRequest deviceRequestDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Device device, DeviceRequest deviceRequest);
}

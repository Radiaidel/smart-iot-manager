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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastCommunication", ignore = true)
    @Mapping(target = "zone", ignore = true)
    void updateEntity(@MappingTarget Device device, DeviceRequest deviceRequest);

    @AfterMapping
    default void setZoneAfterMapping(@MappingTarget Device device, DeviceRequest deviceRequest) {
        if (deviceRequest.getZoneId() != null) {
            if (device.getZone() == null) {
                device.setZone(new com.era.smart_iot_manager.domain.entities.Zone());
            }
            device.getZone().setId(deviceRequest.getZoneId());
        }
    }
}


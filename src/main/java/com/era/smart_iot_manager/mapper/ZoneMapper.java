package com.era.smart_iot_manager.mapper;


import com.era.smart_iot_manager.dto.request.ZoneRequest;
import com.era.smart_iot_manager.dto.response.ZoneResponse;
import com.era.smart_iot_manager.domain.entities.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ZoneMapper {

    @Mapping(target = "id", ignore = true)
    Zone toEntity(ZoneRequest zoneRequest);

    ZoneResponse toResponseDto(Zone zone);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ZoneRequest zoneRequestDto, @MappingTarget Zone zone);
}


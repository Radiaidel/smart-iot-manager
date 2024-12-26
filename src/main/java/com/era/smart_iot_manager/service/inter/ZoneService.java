package com.era.smart_iot_manager.service;

import com.era.smart_iot_manager.dto.request.ZoneRequest;
import com.era.smart_iot_manager.dto.response.ZoneResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ZoneService {
    ZoneResponse getZone(String id);
    ZoneResponse addZone(ZoneRequest zoneRequestDto);
    ZoneResponse updateZone(String id, ZoneRequest zoneRequestDto);
    void deleteZone(String id);
    Page<ZoneResponse> getAllZones(Pageable pageable);
}



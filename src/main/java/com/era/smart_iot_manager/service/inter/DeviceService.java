package com.era.smart_iot_manager.service.inter;

import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.dto.request.DeviceRequest;
import com.era.smart_iot_manager.dto.response.DeviceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceService {
    Page<DeviceResponse> getAllDevices(Pageable pageable);
    Page<DeviceResponse> getDevicesByZone(String zoneId, Pageable pageable);
    DeviceResponse addDevice(DeviceRequest device);
    DeviceResponse getDeviceById(String id);
    DeviceResponse updateDevice(String id, DeviceRequest deviceDetails);
    void deleteDevice(String id);
}

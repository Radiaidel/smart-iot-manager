package com.era.smart_iot_manager.service.inter;

import com.era.smart_iot_manager.domain.entities.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeviceService {
    Page<Device> getAllDevices(Pageable pageable);
    Page<Device> getDevicesByZone(String zoneId, Pageable pageable);
    Device addDevice(Device device);
    Device getDeviceById(String id);
    Device updateDevice(String id, Device deviceDetails);
    void deleteDevice(String id);
}

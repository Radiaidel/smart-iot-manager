package com.era.smart_iot_manager.service.impl;


import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.mapper.DeviceMapper;
import com.era.smart_iot_manager.repository.DeviceRepository;
import com.era.smart_iot_manager.service.inter.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public Page<Device> getAllDevices(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Device> getDevicesByZone(String zoneId, Pageable pageable) {
        return null;
    }

    @Override
    public Device addDevice(Device device) {
        return null;
    }

    @Override
    public Device getDeviceById(String id) {
        return null;
    }

    @Override
    public Device updateDevice(String id, Device deviceDetails) {
        return null;
    }

    @Override
    public void deleteDevice(String id) {

    }
}

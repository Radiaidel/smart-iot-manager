package com.era.smart_iot_manager.service.impl;


import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.dto.request.DeviceRequest;
import com.era.smart_iot_manager.dto.response.DeviceResponse;
import com.era.smart_iot_manager.exception.EntityNotFoundException;
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
    public Page<DeviceResponse> getAllDevices(Pageable pageable) {
        log.info("Fetching all devices with pagination");
        return deviceRepository.findAll(pageable).map(deviceMapper::toResponseDto);
    }

    @Override
    public Page<DeviceResponse> getDevicesByZone(String zoneId, Pageable pageable) {
        log.info("Fetching devices for zone: {}", zoneId);
        return deviceRepository.findByZoneId(zoneId, pageable).map(deviceMapper::toResponseDto);
    }

    @Override
    public DeviceResponse addDevice(DeviceRequest device) {
        Device deviceEntity = deviceMapper.toEntity(device);
        log.info("Adding new device: {}", device.getName());
        Device device1 =deviceRepository.save(deviceEntity);
        return deviceMapper.toResponseDto(device1);
    }

    @Override
    public DeviceResponse getDeviceById(String id) {
        log.info("Fetching device with id: {}", id);
        Device device= deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found with id: " + id));
        return deviceMapper.toResponseDto(device);
    }

    @Override
    public DeviceResponse updateDevice(String id, DeviceRequest deviceDetails) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Device not found with id: " + id));

        deviceMapper.updateEntity(device, deviceDetails);
        Device updatedDevice = deviceRepository.save(device);
        return deviceMapper.toResponseDto(updatedDevice);
    }

    @Override
    public void deleteDevice(String id) {
        log.info("Deleting device with id: {}", id);
        deviceRepository.deleteById(id);
    }
}

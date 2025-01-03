package com.era.smart_iot_manager.controller;


import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.dto.request.DeviceRequest;
import com.era.smart_iot_manager.dto.response.DeviceResponse;
import com.era.smart_iot_manager.service.inter.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class DeviceController {
    private static final Logger log = LoggerFactory.getLogger(DeviceController.class);
    private final DeviceService deviceService;

    @GetMapping({"/user/devices" , "/admin/devices"} )
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<DeviceResponse>> getAllDevices(Pageable pageable) {
        Page<DeviceResponse> devices = deviceService.getAllDevices(pageable);
        return ResponseEntity.ok(devices);
    }

    @GetMapping({"/user/devices/zone/{zoneId}" ,"/admin/devices/zone/{zoneId}"})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<DeviceResponse>> getDevicesByZone(@PathVariable String zoneId, Pageable pageable) {
        Page<DeviceResponse> devices = deviceService.getDevicesByZone(zoneId, pageable);
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/admin/devices")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeviceResponse> addDevice(@Valid @RequestBody DeviceRequest device) {
        log.info(device.getName());
        DeviceResponse newDevice = deviceService.addDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDevice);
    }

    @GetMapping({"/user/devices/{id}" ,"/admin/devices/{id}"})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<DeviceResponse> getDeviceById(@PathVariable String id) {
        DeviceResponse device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    @PutMapping("/admin/devices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DeviceResponse> updateDevice(@PathVariable String id, @Valid @RequestBody DeviceRequest deviceDetails) {
        DeviceResponse updatedDevice = deviceService.updateDevice(id, deviceDetails);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/admin/devices/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}


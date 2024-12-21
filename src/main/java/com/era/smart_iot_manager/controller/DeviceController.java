package com.era.smart_iot_manager.controller;


import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.service.inter.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class DeviceController {
  private final DeviceService deviceService;

    @GetMapping("/user/devices")
    public ResponseEntity<Page<Device>> getAllDevices(Pageable pageable) {
        Page<Device> devices = deviceService.getAllDevices(pageable);
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/user/devices/zone/{zoneId}")
    public ResponseEntity<Page<Device>> getDevicesByZone(@PathVariable String zoneId, Pageable pageable) {
        Page<Device> devices = deviceService.getDevicesByZone(zoneId, pageable);
        return ResponseEntity.ok(devices);
    }

    @PostMapping("/admin/devices")
    public ResponseEntity<Device> addDevice(@Valid @RequestBody Device device) {
        Device newDevice = deviceService.addDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDevice);
    }

    @GetMapping("/user/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable String id) {
        Device device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    @PutMapping("/admin/devices/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable String id, @Valid @RequestBody Device deviceDetails) {
        Device updatedDevice = deviceService.updateDevice(id, deviceDetails);
        return ResponseEntity.ok(updatedDevice);
    }

    @DeleteMapping("/admin/devices/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}


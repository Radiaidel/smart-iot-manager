package com.era.smart_iot_manager.controller;

import com.era.smart_iot_manager.dto.request.ZoneRequest;
import com.era.smart_iot_manager.dto.response.ZoneResponse;
import com.era.smart_iot_manager.service.ZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ZoneController {
    private final ZoneService zoneService;

    @GetMapping({"/user/zones/{id}", "/admin/zones/{id}"})
    public ResponseEntity<ZoneResponse> getZone(@PathVariable String id) {
        return ResponseEntity.ok(zoneService.getZone(id));
    }

    @PostMapping("/admin/zones")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ZoneResponse> addZone(@Valid @RequestBody ZoneRequest zoneRequest) {
        return new ResponseEntity<>(zoneService.addZone(zoneRequest), HttpStatus.CREATED);
    }

    @PutMapping("/admin/zones/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ZoneResponse> updateZone(@PathVariable String id, @Valid @RequestBody ZoneRequest zoneRequest) {
        return ResponseEntity.ok(zoneService.updateZone(id, zoneRequest));
    }

    @DeleteMapping("/admin/zones/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteZone(@PathVariable String id) {
        zoneService.deleteZone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping({"/user/zones", "/admin/zones"})
    public ResponseEntity<Page<ZoneResponse>> getAllZones(Pageable pageable) {
        return ResponseEntity.ok(zoneService.getAllZones(pageable));
    }
}


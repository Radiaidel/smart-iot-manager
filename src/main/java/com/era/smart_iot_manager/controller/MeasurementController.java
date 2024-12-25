package com.era.smart_iot_manager.controller;

import com.era.smart_iot_manager.dto.request.MeasurementRequest;
import com.era.smart_iot_manager.dto.response.MeasurementResponse;
import com.era.smart_iot_manager.service.inter.MeasurementService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MeasurementController {
    private static final Logger log = LoggerFactory.getLogger(MeasurementController.class);
    private final MeasurementService measurementService;

    @GetMapping({"/user/measurements", "/admin/measurements"})
    public ResponseEntity<Page<MeasurementResponse>> getMeasurements(Pageable pageable) {
        return ResponseEntity.ok(measurementService.getMeasurements(pageable));
    }

    @PostMapping({"/user/measurements", "/admin/measurements"})
    public ResponseEntity<MeasurementResponse> addMeasurement(@Valid @RequestBody MeasurementRequest measurementRequestDto) {
        log.info(measurementRequestDto.getDeviceId());
        return new ResponseEntity<>(measurementService.addMeasurement(measurementRequestDto), HttpStatus.CREATED);
    }

    @GetMapping({"/user/measurements/device/{deviceId}", "/admin/measurements/device/{deviceId}"})
    public ResponseEntity<Page<MeasurementResponse>> getMeasurementsByDevice(@PathVariable String deviceId, Pageable pageable) {
        return ResponseEntity.ok(measurementService.getMeasurementsByDevice(deviceId, pageable));
    }

    @GetMapping({"/user/measurements/export", "/admin/measurements/export"})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void exportMeasurements(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"measurements.csv\"");
        measurementService.exportMeasurementsCsv(response.getWriter());
    }
}
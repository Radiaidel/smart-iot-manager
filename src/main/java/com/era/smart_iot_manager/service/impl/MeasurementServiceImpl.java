package com.era.smart_iot_manager.service.impl;

import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.domain.entities.Measurement;
import com.era.smart_iot_manager.dto.request.MeasurementRequest;
import com.era.smart_iot_manager.dto.response.MeasurementResponse;
import com.era.smart_iot_manager.exception.ResourceNotFoundException;
import com.era.smart_iot_manager.mapper.MeasurementMapper;
import com.era.smart_iot_manager.repository.DeviceRepository;
import com.era.smart_iot_manager.repository.MeasurementRepository;
import com.era.smart_iot_manager.service.inter.AlertService;
import com.era.smart_iot_manager.service.inter.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Writer;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
    private static final Logger logger = LoggerFactory.getLogger(MeasurementServiceImpl.class);
    private final MeasurementRepository measurementRepository;
    private final DeviceRepository deviceRepository;
    private final MeasurementMapper measurementMapper;
    private final AlertService alertService;

    @Override
    public Page<MeasurementResponse> getMeasurements(Pageable pageable) {
        logger.info("Fetching all measurements with pagination");
        Page<Measurement> measurementPage = measurementRepository.findAll(pageable);
        return measurementPage.map(measurementMapper::toResponseDto);
    }

    @Override
    @Transactional
    public MeasurementResponse addMeasurement(MeasurementRequest measurementRequestDto) {
        logger.info("Adding new measurement for device: {}", measurementRequestDto.getDeviceId());
        Device device = deviceRepository.findById(measurementRequestDto.getDeviceId())
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + measurementRequestDto.getDeviceId()));

        Measurement measurement = measurementMapper.toEntity(measurementRequestDto);
        measurement.setDevice(device);
        Measurement savedMeasurement = measurementRepository.save(measurement);

        // Check and generate alert
        alertService.checkAndGenerateAlert(device, savedMeasurement);

        return measurementMapper.toResponseDto(savedMeasurement);
    }

    @Override
    public Page<MeasurementResponse> getMeasurementsByDevice(String deviceId, Pageable pageable) {
        logger.info("Fetching measurements for device: {}", deviceId);
        Page<Measurement> measurementPage = measurementRepository.findByDeviceId(deviceId, pageable);
        return measurementPage.map(measurementMapper::toResponseDto);
    }

    @Override
    public MeasurementResponse getMeasurementById(String id) {
        logger.info("Fetching measurement with id: {}", id);
        Measurement measurement = measurementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Measurement not found with id: " + id));
        return measurementMapper.toResponseDto(measurement);
    }

    @Override
    @Transactional
    public void deleteMeasurement(String id) {
        logger.info("Deleting measurement with id: {}", id);
        if (!measurementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Measurement not found with id: " + id);
        }
        measurementRepository.deleteById(id);
    }

    @Override
    public void exportMeasurementsCsv(Writer writer) throws IOException {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                .withHeader("ID", "Timestamp", "Value", "Device ID", "Device Name", "Device Type"))) {
            for (Measurement measurement : measurementRepository.findAll()) {
                csvPrinter.printRecord(
                        measurement.getId(),
                        measurement.getTimestamp(),
                        measurement.getValue(),
                        measurement.getDevice().getId(),
                        measurement.getDevice().getName(),
                        measurement.getDevice().getType()
                );
            }
        } catch (IOException e) {
            logger.error("Error while writing CSV", e);
            throw e;
        }
    }
}


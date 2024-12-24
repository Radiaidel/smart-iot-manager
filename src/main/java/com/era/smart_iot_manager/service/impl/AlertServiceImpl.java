package com.era.smart_iot_manager.service.impl;


import com.era.smart_iot_manager.domain.Enum.AlertSeverity;
import com.era.smart_iot_manager.domain.Enum.DeviceType;
import com.era.smart_iot_manager.domain.entities.Alert;
import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.dto.response.AlertResponse;
import com.era.smart_iot_manager.mapper.AlertMapper;
import com.era.smart_iot_manager.repository.AlertRepository;
import com.era.smart_iot_manager.service.inter.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {
    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;

    @Override
    public Page<AlertResponse> getAllAlerts(Pageable pageable) {
        return null;
    }

    @Override
    public AlertResponse getAlertById(String id) {
        return null;
    }
    @Override
    public void checkAndGenerateAlert(Device device, Measurement measurement) {
        AlertSeverity severity;
        String message;

        if (device.getType() == DeviceType.TEMPERATURE_SENSOR) {
            severity = getTemperatureAlertSeverity(measurement.getValue());
            message = getTemperatureAlertMessage(severity);
        } else if (device.getType() == DeviceType.HUMIDITY_SENSOR) {
            severity = getHumidityAlertSeverity(measurement.getValue());
            message = getHumidityAlertMessage(severity);
        } else {
            log.warn("Unknown device type: {}", device.getType());
            return;
        }

        if (severity != AlertSeverity.NORMAL) {
            Alert alert = new Alert();
            alert.setSeverity(severity);
            alert.setMessage(message);
            alert.setTimestamp(LocalDateTime.now());
            alert.setDevice(device);
            alertRepository.save(alert);
            log.info("Generated alert: {} for device: {}", severity, device.getId());
        }
    }

    private AlertSeverity getTemperatureAlertSeverity(double temperature) {
        if (temperature > 40 || temperature < -10) return AlertSeverity.CRITICAL;
        if (temperature > 35 || temperature < -5) return AlertSeverity.HIGH;
        if (temperature > 30 || temperature < 0) return AlertSeverity.MEDIUM;
        if (temperature > 25) return AlertSeverity.LOW;
        return AlertSeverity.NORMAL;
    }

    private String getTemperatureAlertMessage(AlertSeverity severity) {
        switch (severity) {
            case CRITICAL: return "Risque immédiat pour les équipements";
            case HIGH: return "Situation préoccupante nécessitant une action rapide";
            case MEDIUM: return "Situation à surveiller";
            case LOW: return "Légère déviation des valeurs optimales";
            default: return "Température dans la plage optimale";
        }
    }

    private AlertSeverity getHumidityAlertSeverity(double humidity) {
        if (humidity > 90 || humidity < 20) return AlertSeverity.CRITICAL;
        if (humidity > 80 || humidity < 30) return AlertSeverity.HIGH;
        if (humidity > 70 || humidity < 40) return AlertSeverity.MEDIUM;
        if (humidity > 65 || humidity < 45) return AlertSeverity.LOW;
        return AlertSeverity.NORMAL;
    }

    private String getHumidityAlertMessage(AlertSeverity severity) {
        switch (severity) {
            case CRITICAL: return "Risque de dommages matériels";
            case HIGH: return "Conditions défavorables";
            case MEDIUM: return "Situation à surveiller";
            case LOW: return "Légère déviation";
            default: return "Humidité dans la plage optimale";
        }
    }
}


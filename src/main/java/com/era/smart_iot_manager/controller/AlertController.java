package com.era.smart_iot_manager.controller;


import com.era.smart_iot_manager.domain.entities.Alert;
import com.era.smart_iot_manager.service.inter.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class AlertController {

    private final AlertService alertService;

    @GetMapping("/user/alerts")
    public ResponseEntity<Page<Alert>> getAllAlerts(Pageable pageable) {
        Page<Alert> alerts = alertService.getAllAlerts(pageable);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/user/alerts/{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable String id) {
        Alert alert = alertService.getAlertById(id);
        return ResponseEntity.ok(alert);
    }

}

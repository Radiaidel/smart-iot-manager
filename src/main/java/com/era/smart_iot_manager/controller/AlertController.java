package com.era.smart_iot_manager.controller;


import com.era.smart_iot_manager.dto.response.AlertResponse;
import com.era.smart_iot_manager.service.inter.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping({"/user/alerts" , "/admin/alerts"} )
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<AlertResponse>> getAllAlerts(Pageable pageable) {
        Page<AlertResponse> alerts = alertService.getAllAlerts(pageable);
        return ResponseEntity.ok(alerts);
    }

    @GetMapping({"/user/alerts/{id}", "/admin/alerts/{id}"})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<AlertResponse> getAlertById(@PathVariable String id) {
        AlertResponse alert = alertService.getAlertById(id);
        return ResponseEntity.ok(alert);
    }

}

package com.era.smart_iot_manager.service.inter;

import com.era.smart_iot_manager.domain.entities.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertService {
    Page<Alert> getAllAlerts(Pageable pageable);
    Alert getAlertById(String id);
}

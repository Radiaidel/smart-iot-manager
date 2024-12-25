package com.era.smart_iot_manager.service.inter;

import com.era.smart_iot_manager.domain.entities.Device;
import com.era.smart_iot_manager.dto.response.AlertResponse;
import com.era.smart_iot_manager.domain.entities.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlertService {
    Page<AlertResponse> getAllAlerts(Pageable pageable);
    AlertResponse getAlertById(String id);
    void checkAndGenerateAlert(Device device, Measurement measurement);
}

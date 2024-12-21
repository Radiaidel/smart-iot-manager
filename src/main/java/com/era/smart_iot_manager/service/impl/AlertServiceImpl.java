package com.era.smart_iot_manager.service.impl;


import com.era.smart_iot_manager.domain.entities.Alert;
import com.era.smart_iot_manager.mapper.AlertMapper;
import com.era.smart_iot_manager.repository.AlertRepository;
import com.era.smart_iot_manager.service.inter.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {
    private final AlertRepository alertRepository;
    private final AlertMapper alertMapper;

    @Override
    public Page<Alert> getAllAlerts(Pageable pageable) {
        return null;
    }

    @Override
    public Alert getAlertById(String id) {
        return null;
    }
}

package com.era.smart_iot_manager.service.inter;


import com.era.smart_iot_manager.dto.request.MeasurementRequest;
import com.era.smart_iot_manager.dto.response.MeasurementResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.Writer;

public interface MeasurementService {
    Page<MeasurementResponse> getMeasurements(Pageable pageable);
    MeasurementResponse addMeasurement(MeasurementRequest measurementRequestDto);
    Page<MeasurementResponse> getMeasurementsByDevice(String deviceId, Pageable pageable);
    MeasurementResponse getMeasurementById(String id);
    void deleteMeasurement(String id);
    void exportMeasurementsCsv(Writer writer) throws IOException;

}
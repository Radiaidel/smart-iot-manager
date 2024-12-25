package com.era.smart_iot_manager.service.impl;

import com.era.smart_iot_manager.dto.request.ZoneRequest;
import com.era.smart_iot_manager.dto.response.ZoneResponse;
import com.era.smart_iot_manager.exception.ResourceNotFoundException;
import com.era.smart_iot_manager.mapper.ZoneMapper;
import com.era.smart_iot_manager.domain.entities.Zone;
import com.era.smart_iot_manager.repository.ZoneRepository;
import com.era.smart_iot_manager.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {
    private static final Logger logger = LoggerFactory.getLogger(ZoneServiceImpl.class);
    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public ZoneResponse getZone(String id) {
        logger.info("Fetching zone with id: {}", id);
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
        return zoneMapper.toResponseDto(zone);
    }

    @Override
    @Transactional
    public ZoneResponse addZone(ZoneRequest zoneRequest) {
        logger.info("Adding new zone: {}", zoneRequest.getName());
        Zone zone = zoneMapper.toEntity(zoneRequest);
        Zone savedZone = zoneRepository.save(zone);
        return zoneMapper.toResponseDto(savedZone);
    }

    @Override
    @Transactional
    public ZoneResponse updateZone(String id, ZoneRequest zoneRequest) {
        logger.info("Updating zone with id: {}", id);
        Zone existingZone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
        zoneMapper.updateEntityFromDto(zoneRequest, existingZone);
        Zone updatedZone = zoneRepository.save(existingZone);
        return zoneMapper.toResponseDto(updatedZone);
    }

    @Override
    @Transactional
    public void deleteZone(String id) {
        logger.info("Deleting zone with id: {}", id);
        if (!zoneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Zone not found with id: " + id);
        }
        zoneRepository.deleteById(id);
    }

    @Override
    public Page<ZoneResponse> getAllZones(Pageable pageable) {
        logger.info("Fetching all zones with pagination");
        Page<Zone> zonePage = zoneRepository.findAll(pageable);
        return zonePage.map(zoneMapper::toResponseDto);
    }
}

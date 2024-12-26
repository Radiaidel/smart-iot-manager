package com.era.smart_iot_manager.service;


import com.era.smart_iot_manager.dto.request.LoginRequest;
import com.era.smart_iot_manager.dto.request.RegisterRequest;
import com.era.smart_iot_manager.dto.response.JwtResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public interface AuthService {

    JwtResponse login(LoginRequest request);

    @Transactional
    JwtResponse register(RegisterRequest request);
}
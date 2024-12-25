package com.era.smart_iot_manager.service.impl;


import com.era.smart_iot_manager.dto.request.LoginRequest;
import com.era.smart_iot_manager.dto.request.RegisterRequest;
import com.era.smart_iot_manager.dto.response.JwtResponse;
import com.era.smart_iot_manager.exception.UserAlreadyExistsException;
import com.era.smart_iot_manager.domain.entities.Role;
import com.era.smart_iot_manager.domain.entities.User;
import com.era.smart_iot_manager.repository.RoleRepository;
import com.era.smart_iot_manager.repository.UserRepository;
import com.era.smart_iot_manager.security.jwt.JwtUtils;
import com.era.smart_iot_manager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByLogin(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        return JwtResponse.builder()
                .token(jwt)
                .login(user.getLogin())
                .roles(roles)
                .build();
    }

    @Override
    @Transactional
    public JwtResponse register(RegisterRequest request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        Role userRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = User.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .roles(Set.of(userRole))
                .build();

        userRepository.save(user);

        return login(LoginRequest.builder().login(request.getLogin()).password(request.getPassword()).build());

    }
}
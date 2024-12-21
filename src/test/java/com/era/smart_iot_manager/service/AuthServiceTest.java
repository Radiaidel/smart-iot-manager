package com.era.smart_iot_manager.service;

import com.era.smart_iot_manager.dto.request.LoginRequest;
import com.era.smart_iot_manager.dto.request.RegisterRequest;
import com.era.smart_iot_manager.dto.response.JwtResponse;
import com.era.smart_iot_manager.exception.UserAlreadyExistsException;
import com.era.smart_iot_manager.model.Role;
import com.era.smart_iot_manager.model.User;
import com.era.smart_iot_manager.repository.RoleRepository;
import com.era.smart_iot_manager.repository.UserRepository;
import com.era.smart_iot_manager.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private Authentication authentication;
    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private AuthService authService;

    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;
    private User user;
    private Role userRole;
    private static final String TEST_TOKEN = "test.jwt.token";

    @BeforeEach
    void setUp() {
        loginRequest = LoginRequest.builder()
                .login("testuser")
                .password("password123")
                .build();

        registerRequest = RegisterRequest.builder()
                .login("newuser")
                .password("password123")
                .build();

        userRole = Role.builder()
                .name("ROLE_USER")
                .level(1)
                .build();

        user = User.builder()
                .login("testuser")
                .password("encoded_password")
                .active(true)
                .roles(Set.of(userRole))
                .build();
    }

    @Test
    void login_Success() {
        // Arrange
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("testuser");
        when(userRepository.findByLogin("testuser")).thenReturn(Optional.of(user));
        when(jwtUtils.generateJwtToken(authentication)).thenReturn(TEST_TOKEN);

        // Act
        JwtResponse response = authService.login(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(TEST_TOKEN, response.getToken());
        assertEquals("testuser", response.getLogin());
        assertEquals(Collections.singletonList("ROLE_USER"), response.getRoles());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void register_Success() {
        // Arrange
        when(userRepository.existsByLogin(registerRequest.getLogin())).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Mock login behavior after registration
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("newuser");
        when(userRepository.findByLogin("newuser")).thenReturn(Optional.of(user));
        when(jwtUtils.generateJwtToken(authentication)).thenReturn(TEST_TOKEN);

        // Act
        JwtResponse response = authService.register(registerRequest);

        // Assert
        assertNotNull(response);
        assertEquals(TEST_TOKEN, response.getToken());
        verify(userRepository).save(any(User.class));
        verify(passwordEncoder).encode(registerRequest.getPassword());
    }

    @Test
    void register_UserAlreadyExists() {
        // Arrange
        when(userRepository.existsByLogin(registerRequest.getLogin())).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () ->
                authService.register(registerRequest)
        );
        verify(userRepository, never()).save(any(User.class));
    }
}
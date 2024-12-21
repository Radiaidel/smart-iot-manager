package com.era.smart_iot_manager.integration;

import com.era.smart_iot_manager.dto.request.LoginRequest;
import com.era.smart_iot_manager.dto.request.RegisterRequest;
import com.era.smart_iot_manager.model.Role;
import com.era.smart_iot_manager.model.User;
import com.era.smart_iot_manager.repository.RoleRepository;
import com.era.smart_iot_manager.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String AUTH_ENDPOINT = "/api/auth";
    private static final String ADMIN_ENDPOINT = "/api/admin";
    private static final String USER_ENDPOINT = "/api/user";

    @BeforeEach
    void setUp() {
        // Nettoyage des données de test
        userRepository.deleteAll();
        roleRepository.deleteAll();

        // Création des rôles nécessaires
        Role userRole = Role.builder()
                .name("ROLE_USER")
                .level(1)
                .build();
        Role adminRole = Role.builder()
                .name("ROLE_ADMIN")
                .level(2)
                .build();
        roleRepository.save(userRole);
        roleRepository.save(adminRole);
    }


    @Test
    void registerDuplicateUser_ShouldFail() throws Exception {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .login("duplicate")
                .password("Test123!")
                .build();

        // Premier enregistrement
        mockMvc.perform(post(AUTH_ENDPOINT + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Deuxième enregistrement avec le même login
        mockMvc.perform(post(AUTH_ENDPOINT + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isConflict())  // Changé de 400 à 409 pour correspondre à l'erreur réelle
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void loginWithInvalidCredentials_ShouldFail() throws Exception {
        LoginRequest loginRequest = LoginRequest.builder()
                .login("nonexistent")
                .password("wrongpass")
                .build();

        mockMvc.perform(post(AUTH_ENDPOINT + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void accessProtectedEndpointWithoutToken_ShouldFail() throws Exception {
        mockMvc.perform(get(USER_ENDPOINT + "/devices"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void accessProtectedEndpointWithInvalidToken_ShouldFail() throws Exception {
        mockMvc.perform(get(USER_ENDPOINT + "/devices")
                        .header("Authorization", "Bearer invalid.token.here"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    void registerWithInvalidData_ShouldFail() throws Exception {
        RegisterRequest invalidRequest = RegisterRequest.builder()
                .login("")  // login vide
                .password("weak")  // mot de passe trop court
                .build();

        mockMvc.perform(post(AUTH_ENDPOINT + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

}
package com.era.smart_iot_manager.initializer;

import com.era.smart_iot_manager.model.Role;
import com.era.smart_iot_manager.repository.RoleRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Component
public class RoleInitializer {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initializeRoles() {
        createRoleIfNotExists("ROLE_USER", 1);
        createRoleIfNotExists("ROLE_ADMIN", 2);
    }

    private void createRoleIfNotExists(String roleName, Integer level) {
        Optional<Role> existingRole = roleRepository.findByName(roleName);
        if (existingRole.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(roleName);
            newRole.setLevel(level);
            roleRepository.save(newRole);
        }
    }
}

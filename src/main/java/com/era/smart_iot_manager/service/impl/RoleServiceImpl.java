package com.era.smart_iot_manager.service.impl;

import com.era.smart_iot_manager.repository.RoleRepository;
import com.era.smart_iot_manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean roleExists(String roleId) {
        return roleRepository.existsById(roleId);
    }
}

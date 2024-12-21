package com.era.smart_iot_manager.service.impl;

import com.era.smart_iot_manager.model.User;
import com.era.smart_iot_manager.model.Role;
import com.era.smart_iot_manager.repository.UserRepository;
import com.era.smart_iot_manager.repository.RoleRepository;
import com.era.smart_iot_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public User updateUserRoles(String userId, Set<Role> roles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        for (Role role : roles) {
            if (roleRepository.existsById(role.getId())) {
                user.getRoles().add(role);
            } else {
                throw new RuntimeException("Role not found with id: " + role.getId());
            }
        }

        return userRepository.save(user);
    }
}

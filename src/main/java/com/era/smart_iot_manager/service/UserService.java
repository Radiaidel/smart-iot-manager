package com.era.smart_iot_manager.service;

import com.era.smart_iot_manager.model.Role;
import com.era.smart_iot_manager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Set;

public interface UserService {
    Page<User> getAllUsers(PageRequest pageRequest);

    User updateUserRoles(String userId, Set<Role> roles);
}

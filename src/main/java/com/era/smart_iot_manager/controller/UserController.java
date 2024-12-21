package com.era.smart_iot_manager.controller;

import com.era.smart_iot_manager.model.User;
import com.era.smart_iot_manager.model.Role;
import com.era.smart_iot_manager.service.UserService;
import com.era.smart_iot_manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "login") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {

        Sort sort = Sort.by(Sort.Order.by(sortBy));
        if ("DESC".equalsIgnoreCase(sortDir)) {
            sort = sort.descending();
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<User> users = userService.getAllUsers(pageRequest);

        return ResponseEntity.ok(users);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}/roles")
    public ResponseEntity<User> updateUserRoles(@PathVariable String id, @RequestBody Set<Role> roles) {
        User updatedUser = userService.updateUserRoles(id, roles);
        return ResponseEntity.ok(updatedUser);
    }
}

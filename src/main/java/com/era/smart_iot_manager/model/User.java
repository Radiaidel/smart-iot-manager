package com.era.smart_iot_manager.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Document(collection = "users")
public class User {
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String login;
    
    private String password;
    private boolean active;
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
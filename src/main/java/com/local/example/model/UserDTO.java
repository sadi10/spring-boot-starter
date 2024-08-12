package com.local.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private boolean enabled;
    private Set<String> authorities;

    // Getters and Setters
}

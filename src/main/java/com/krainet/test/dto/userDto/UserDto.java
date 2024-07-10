package com.krainet.test.dto.userDto;

import com.krainet.test.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles = new HashSet<>();
}

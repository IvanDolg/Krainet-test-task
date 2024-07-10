package com.krainet.test.service;

import com.krainet.test.dto.userDto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserDto save(UserDto userDto);
    UserDto update(UserDto userDto, Long id);
    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    void deleteById(Long id);
}

package com.krainet.test.service;

import com.krainet.test.dto.userDto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto save(UserDto userDto);
    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    void deleteById(Long id);
}

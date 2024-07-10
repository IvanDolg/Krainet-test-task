package com.krainet.test.service.impl;

import com.krainet.test.dto.userDto.UserDto;
import com.krainet.test.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return List.of();
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }
}

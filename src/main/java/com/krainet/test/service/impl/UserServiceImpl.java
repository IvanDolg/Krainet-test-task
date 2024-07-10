package com.krainet.test.service.impl;

import com.krainet.test.dto.userDto.UserDto;
import com.krainet.test.entity.Role;
import com.krainet.test.entity.User;
import com.krainet.test.mapper.AutoUserMapper;
import com.krainet.test.repository.UserRepository;
import com.krainet.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.getRoles().add(Role.USER);

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
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

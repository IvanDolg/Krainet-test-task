package com.krainet.test.service.impl;

import com.krainet.test.dto.userDto.UserDto;
import com.krainet.test.entity.Role;
import com.krainet.test.entity.User;
import com.krainet.test.exception.RecurseNotFoundException;
import com.krainet.test.mapper.AutoUserMapper;
import com.krainet.test.repository.UserRepository;
import com.krainet.test.security.UserPrincipal;
import com.krainet.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());

        if (optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User already exists");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.getRoles().add(Role.USER);

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("User", "id", String.valueOf(id))
        );

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(user);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto).toList();
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("User", "id", String.valueOf(id))
        );
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("User", "id", String.valueOf(id))
        );

        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserPrincipal.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}

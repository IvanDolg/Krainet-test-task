package com.krainet.test.controller;

import com.krainet.test.dto.userDto.LoginUserDto;
import com.krainet.test.dto.userDto.UserDto;
import com.krainet.test.entity.Role;
import com.krainet.test.security.JWTTokenProvider;
import com.krainet.test.security.UserPrincipal;
import com.krainet.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;

    @PostMapping("/registration")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.save(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserDto dto) {
        UserPrincipal userPrincipal = (UserPrincipal) userService.loadUserByUsername(dto.getUsername());

        if (passwordEncoder.matches(dto.getPassword(), userPrincipal.getPassword())) {
            Set<Role> authorities = (Set<Role>) userPrincipal.getAuthorities();
            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername(), userPrincipal.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto,
                                          @PathVariable Long id) {
        UserDto updatedUser = userService.update(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

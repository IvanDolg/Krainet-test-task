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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
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
            LocalDateTime time = LocalDateTime.now();
            //mapper.mapToUser(dto).setLastVisitDate(time);
            String token = jwtTokenProvider.generateToken(userPrincipal.getUsername(), userPrincipal.getPassword(), authorities);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

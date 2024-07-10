package com.krainet.test.controller;

import com.krainet.test.dto.userDto.UserDto;
import com.krainet.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.save(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}

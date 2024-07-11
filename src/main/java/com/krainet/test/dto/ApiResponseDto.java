package com.krainet.test.dto;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.dto.record.RecordDto;
import com.krainet.test.dto.userDto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {
    private RecordDto recordDto;
    private ProjectDto projectDto;
    private UserDto userDto;
}

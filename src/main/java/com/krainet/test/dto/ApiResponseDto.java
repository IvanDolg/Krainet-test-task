package com.krainet.test.dto;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.dto.record.RecordDto;
import com.krainet.test.dto.userDto.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Response API dto model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto {

    @Schema(
            description = "Record data"
    )
    private RecordDto recordDto;

    @Schema(
            description = "Project data"
    )
    private ProjectDto projectDto;

    @Schema(
            description = "User data"
    )
    private UserDto userDto;
}
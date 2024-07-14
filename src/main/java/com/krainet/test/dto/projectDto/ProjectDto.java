package com.krainet.test.dto.projectDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        description = "Response project dto model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    @Schema(
            description = "Unique identifier of the project"
    )
    private Long id;

    @Schema(
            description = "Name of the project"
    )
    private String name;

    @Schema(
            description = "Description of the project"
    )
    private String description;
}
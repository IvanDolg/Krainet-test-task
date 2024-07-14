package com.krainet.test.dto.userDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(
        description = "Response login user dto model information"
)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {

    @Schema(
            description = "Unique identifier of the user"
    )
    private Long id;

    @Schema(
            description = "Username of the user"
    )
    private String username;

    @Schema(
            description = "Password of the user"
    )
    private String password;
}
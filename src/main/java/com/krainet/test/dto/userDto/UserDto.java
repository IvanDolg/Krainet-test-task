package com.krainet.test.dto.userDto;

import com.krainet.test.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Schema(
        description = "Response user dto model information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

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

    @Schema(
            description = "Email of the user"
    )
    private String email;

    @Schema(
            description = "Roles assigned to the user"
    )
    private Set<Role> roles = new HashSet<>();
}
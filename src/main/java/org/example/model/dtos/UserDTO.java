package org.example.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private Long id;

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "email is mandatory")
    private String email;
}

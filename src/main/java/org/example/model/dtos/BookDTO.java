package org.example.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
    private Long id;

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "at least one author is mandatory")
    private String author;

}

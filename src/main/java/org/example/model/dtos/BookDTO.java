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
    @NotNull(message = "The book needs an id")
    private Long id;

    @NotBlank(message = "The book needs a title")
    private String title;

    @NotBlank(message = "The book needs an author")
    private String author;

    private int quantity;
}

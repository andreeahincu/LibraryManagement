package org.example.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDTO {

    @NotBlank(message = "The book needs a name")
    private String name;

    @NotBlank(message = "The book needs an author")
    private String author;

    @Positive(message = "The book needs an ISBN")
    private int ISBN;
}

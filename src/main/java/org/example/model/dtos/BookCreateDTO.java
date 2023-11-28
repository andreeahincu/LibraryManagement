package org.example.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateDTO {
    private Long id;
    private String title;
    private String author;
   // private int quantity;
    private Long userId;
}

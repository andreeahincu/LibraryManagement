package org.example.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookSearchDTO {
    private Long id;
    private String title;
    private String author;
   // private int quantity;
    private UserSearchDTO user;
}

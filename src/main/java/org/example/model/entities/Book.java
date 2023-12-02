package org.example.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "book")
@Table(name = "books")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

//    @ManyToMany(cascade =CascadeType.ALL)
//    @JoinTable(name = "books_users",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private Set<User> users;
}

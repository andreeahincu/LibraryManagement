package org.example.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "user")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
//    @ManyToMany(mappedBy = "users")
//    private Set<Book> bookSet;
}

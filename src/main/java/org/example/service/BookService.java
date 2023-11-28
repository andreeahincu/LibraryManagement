package org.example.service;

import org.example.model.dtos.BookCreateDTO;
import org.example.model.dtos.BookDTO;
import org.example.model.entities.User;
import org.example.repository.BookRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;

    @Autowired
    BookService(BookRepository bookRepository, BookMapper bookMapper, UserRepository userRepository){
        this.bookRepository=bookRepository;
        this.bookMapper=bookMapper;

    }

    public BookDTO createBook(BookCreateDTO bookCreateDTO){
        User user = userRepository.findById(bookCreateDTO.get)
    }
}

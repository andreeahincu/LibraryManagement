package org.example.service;

import org.example.model.dtos.BookCreateDTO;
import org.example.model.dtos.BookDTO;
import org.example.model.entities.Book;
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
        this.userRepository = userRepository;
    }

    public BookDTO createBook(BookCreateDTO bookCreateDTO){
        // validare user (verificare daca exista un user cu id-ul egal cu user_id din request)
        User user = userRepository.findById(bookCreateDTO.getUserId()).orElseThrow() -> new RuntimeException());

        // construim un book entity pe baza bookCreatDTO (request-ul de la client)
        Book book = bookMapper.mapBookDTOtoBook(bookCreateDTO);
        book.setUser(user);

        // salvare book
        Book savedBook = bookRepository.save(book);


    }
}

package org.example.service;

import org.example.model.dtos.BookCreateDTO;
import org.example.model.dtos.BookDTO;
import org.example.model.entities.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book mapBookDTOtoBook(BookDTO bookDTO) {
        return new Book(bookDTO.getId(),
                bookDTO.getAuthor(),
                bookDTO.getTitle());
    }

    public Book mapBookCreateDTOToBook(BookCreateDTO bookCreateDTO){
        Book book = new Book();
        book.setId(bookCreateDTO.getId());
        book.set
    }
    public BookDTO mapBookToBookDTO(Book book) {
        return new BookDTO(book.getId(),
                book.getAuthor(),
                book.getTitle());
    }

}

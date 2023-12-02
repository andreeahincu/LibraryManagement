package org.example.service;

import org.example.model.dtos.BookDTO;
import org.example.model.entities.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book mapBookDTOtoBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        return book;
   }

    public BookDTO mapBookEntityToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        return bookDTO;
    }

}

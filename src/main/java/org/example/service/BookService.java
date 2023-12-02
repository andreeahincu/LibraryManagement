package org.example.service;

import org.example.model.dtos.BookDTO;
import org.example.model.dtos.UserDTO;
import org.example.model.entities.Book;
import org.example.model.entities.User;
import org.example.repository.BookRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @Autowired
    BookService(BookRepository bookRepository, BookMapper bookMapper){
        this.bookRepository=bookRepository;
        this.bookMapper=bookMapper;
    }

    public Book createBook(BookDTO bookDTO){
        Book book = new Book(bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor());
        return bookRepository.createBook(book);
    }

    public Book findBookById(Long id){
        return bookRepository.findBookById(id).orElse(null);
    }

    public List<BookDTO> findBookByTitle(String title){
        return bookRepository.findBookByTitle(title).stream()
                .map(entity ->bookMapper.mapBookEntityToBookDTO(entity))
                .collect(Collectors.toList());
    }

    public List<BookDTO> findBookByAuthor(String author){
        return bookRepository.findBookByTitle(author).stream()
                .map(entity ->bookMapper.mapBookEntityToBookDTO(entity))
                .collect(Collectors.toList());
    }

    public Map<Long, Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO){
        // retrieve the existing book from the repository
        Book existingBook = bookRepository.findBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // update the fields of the existing book with data from the DTO
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());

        // call the method from the repository to persist the changes
        Book updatedBook = bookRepository.updateBook(existingBook);

        // map the updated book entity to a DTO before returning it
        return bookMapper.mapBookEntityToBookDTO(updatedBook);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteBook(id);
    }

}

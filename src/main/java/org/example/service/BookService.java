package org.example.service;

import jakarta.transaction.Transactional;
import org.example.model.dtos.BookDTO;
import org.example.model.dtos.UserDTO;
import org.example.model.entities.Book;
import org.example.model.entities.User;
import org.example.repository.BookRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return bookRepository.save(book);
    }

    public Book findBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }



    public List<BookDTO> getAllBooks(){
        List<BookDTO> BookDTOList = new ArrayList<>();
        for(Book book : bookRepository.findAll() ){
            BookDTOList.add(bookMapper.mapBookEntityToBookDTO(book) );
        }
        return BookDTOList;
    }

    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO){
        // retrieve the existing book from the repository
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // update the fields of the existing book with data from the DTO
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());

        // call the method from the repository to persist the changes
        Book updatedBook = new Book(id, existingBook.getTitle(), existingBook.getAuthor());

        // map the updated book entity to a DTO before returning it
        return bookMapper.mapBookEntityToBookDTO(updatedBook);
    }

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }


//    public List<BookDTO> findBookByTitle(String title){
//        return bookRepository.findBookByTitle(title).stream()
//                .map(entity ->bookMapper.mapBookEntityToBookDTO(entity))
//                .collect(Collectors.toList());
//    }
//
//    public List<BookDTO> findBookByAuthor(String author){
//        return bookRepository.findBookByTitle(author).stream()
//                .map(entity ->bookMapper.mapBookEntityToBookDTO(entity))
//                .collect(Collectors.toList());
//    }
}

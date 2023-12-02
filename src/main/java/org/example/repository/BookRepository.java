package org.example.repository;

import org.example.model.entities.Book;

import org.example.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    // temporar pana la DB
    private long currentBookId=1;
    private Map<Long, Book> bookList = new HashMap<>();

    public Book createBook(Book bookToSave){
        if(bookToSave.getId()==null){
            bookToSave.setId(currentBookId++);
        }
        bookList.put(bookToSave.getId(), bookToSave);
        return bookToSave;
    }

    public Optional<Book> findBookById(Long id){
        return Optional.ofNullable(bookList.get(id));
    }

    public List<Book> findBookByTitle(String title){
        return bookList.values().stream()
                .filter( book -> book.getTitle().contains(title) )
                .collect(Collectors.toList() );
    }

    public List<Book> findBookByAuthor(String author){
        return bookList.values().stream()
                .filter( book -> book.getAuthor().contains(author) )
                .collect(Collectors.toList() );
    }

    public Map<Long, Book> getAllBooks(){
        return bookList;
    }

    public Book updateBook(Book updatedBook){
        Long bookId = updatedBook.getId();
        if( bookId == null || !bookList.containsKey(bookId) ){
            throw new RuntimeException("Book not found for update its details");
        }
        bookList.put(bookId, updatedBook);
        return updatedBook;
    }

    public void deleteBook(Long id){
        bookList.remove(id);
    }


}
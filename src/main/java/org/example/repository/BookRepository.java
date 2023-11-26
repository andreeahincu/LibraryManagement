package org.example.repository;

import org.example.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {
    // temporar pana la DB
    private Map<String, Long> bookList = new HashMap<>();

    public Book createBook(Book bookToSave){
        bookList.put(bookToSave.getTitle(), bookToSave.getId());
        return bookToSave;
    }

    public Map<String, Long> getAllBooks(){
        return bookList;
    }

    public Book updateBook(Book bookToSave){
        bookList.replace(bookToSave.getTitle(), bookToSave.getId());
        return bookToSave;
    }

    public void deleteBook(String title){
        bookList.remove(title);
    }
}
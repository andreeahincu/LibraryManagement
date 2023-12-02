package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.dtos.BookDTO;
import org.example.model.dtos.CustomResponseDTO;
import org.example.service.BookMapper;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v2")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;
    @Autowired
    BookController(BookService bookService, BookMapper bookMapper){
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping(path = "/createBook")
    public ResponseEntity<CustomResponseDTO> createBook(@RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        // check for validation errors
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseObject(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }
        BookDTO createdBook = bookMapper.mapBookEntityToBookDTO(bookService.createBook(bookDTO));

        // set response data
        customResponseDTO.setResponseObject(createdBook);
        customResponseDTO.setResponseMessage("Book created successfully");

        // return a response entity with data and HTTP status
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<CustomResponseDTO> getBookById(@PathVariable Long id) {
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        BookDTO foundBookById = bookMapper.mapBookEntityToBookDTO(bookService.findBookById(id));

        if (Objects.isNull(foundBookById)) {
            customResponseDTO.setResponseMessage("No book was found by this id");
            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
        customResponseDTO.setResponseObject(foundBookById);
        customResponseDTO.setResponseMessage("Book found successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllBooks")
    public List<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<CustomResponseDTO> updateBook(@PathVariable Long bookId, @RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult){
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if( bindingResult.hasErrors() ){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }

        try{
            BookDTO updatedBook = bookService.updateBook(bookId, bookDTO);

            customResponseDTO.setResponseObject(updatedBook);
            customResponseDTO.setResponseMessage("Book updated successfully");

            return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
        } catch(RuntimeException e){
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(e.getMessage());

            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/deleteBook/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return new ResponseEntity("Book deleted", HttpStatus.OK);
    }

//    @GetMapping("/getBooksByTitle/{title}")
//    public ResponseEntity<CustomResponseDTO> getBooksByTitle(@PathVariable String title) {
//
//        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
//
//        List<BookDTO> foundBooksByTitle = bookService.findBookByTitle(title);
//        if( Objects.isNull(foundBooksByTitle) || foundBooksByTitle.isEmpty() ){
//            customResponseDTO.setResponseMessage("No book was found by this title");
//            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
//        }
//        customResponseDTO.setResponseObject(foundBooksByTitle);
//        customResponseDTO.setResponseMessage("Book found successfully");
//        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
//    }
//
//    @GetMapping("/getBooksByAuthor/{author}")
//    public ResponseEntity<CustomResponseDTO> getBooksByAuthor(@PathVariable String author) {
//
//        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
//
//        List<BookDTO> foundBooksByAuthor = bookService.findBookByAuthor(author);
//        if( Objects.isNull(foundBooksByAuthor) || foundBooksByAuthor.isEmpty() ){
//            customResponseDTO.setResponseMessage("No book was found by this title");
//            return new ResponseEntity<>(customResponseDTO, HttpStatus.NOT_FOUND);
//        }
//        customResponseDTO.setResponseObject(foundBooksByAuthor);
//        customResponseDTO.setResponseMessage("Book found successfully");
//        return new ResponseEntity<>(customResponseDTO, HttpStatus.OK);
//    }
}

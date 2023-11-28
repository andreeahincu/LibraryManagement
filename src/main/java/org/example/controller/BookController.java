package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.dtos.BookCreateDTO;
import org.example.model.dtos.BookSearchDTO;
import org.example.model.dtos.CustomResponseDTO;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BookController {
    private final BookService bookService;
    @Autowired
    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping(path = "/book")
    public ResponseEntity<CustomResponseDTO> createBook(@RequestBody @Valid BookCreateDTO bookCreateDTO, BindingResult bindingResult){
        CustomResponseDTO customResponseDTO = new CustomResponseDTO();

        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            customResponseDTO.setResponseObject(null);
            customResponseDTO.setResponseMessage(errorMessage);
            return new ResponseEntity<>(customResponseDTO, HttpStatus.BAD_REQUEST);
        }
        BookSearchDTO bookSearchDTO = bookService.createBook(bookCreateDTO);

        customResponseDTO.setResponseObject(bookSearchDTO);
        customResponseDTO.setResponseMessage("Book made successfully");
        return new ResponseEntity<>(customResponseDTO, HttpStatus.CREATED);
    }
}

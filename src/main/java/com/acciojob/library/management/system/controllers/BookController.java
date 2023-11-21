package com.acciojob.library.management.system.controllers;

import com.acciojob.library.management.system.entitys.Book;
import com.acciojob.library.management.system.enums.Genre;
import com.acciojob.library.management.system.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody Book book, @RequestParam("authorId") Integer authorId) {
        String result;
        try {
            result = bookService.addBook(book, authorId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/getBooksByGenre")
    public ResponseEntity getBooksByGenre(@RequestParam("genre")Genre genre) {
        List<String> booksNameListByGenre = bookService.getBooksByGenre(genre);
        return new ResponseEntity<>(booksNameListByGenre, HttpStatus.OK);
    }
}

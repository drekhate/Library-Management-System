package com.acciojob.library.management.system.controllers;

import com.acciojob.library.management.system.entitys.Author;
import com.acciojob.library.management.system.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping("/addAuthor")
    public ResponseEntity addAuthor(@RequestBody Author author) {
        String result = authorService.addAuthor(author);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @GetMapping("/findAllAuthorName")
    public ResponseEntity findAllAuthorName() {
        List<String> authorList = authorService.findAllAuthorName();
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }
    @GetMapping("/authorById/{authorId}")
    public ResponseEntity authorById(@PathVariable("authorId") Integer authorId) {
        Author author;
        try {
            author = authorService.authorByid(authorId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
    @GetMapping("/authorBookList")
    public ResponseEntity authorBookList(@RequestParam("authorId") Integer authorId) {
        List<String> bookNameList;
        try {
            bookNameList = authorService.authorBookList(authorId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(bookNameList, HttpStatus.OK);
    }
}

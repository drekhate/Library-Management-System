package com.acciojob.library.management.system.services;

import com.acciojob.library.management.system.entitys.Author;
import com.acciojob.library.management.system.entitys.Book;
import com.acciojob.library.management.system.exceptions.AuthorNotFoundException;
import com.acciojob.library.management.system.repositorys.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "author has been added successfully to the db";
    }

    public List<String> findAllAuthorName() {
        List<Author> authorList = authorRepository.findAll();
        List<String> authorNameList = new ArrayList<>();
        for (Author author: authorList) {
            authorNameList.add(author.getAuthorName());
        }
        return authorNameList;
    }

    public Author authorByid(Integer authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (!author.isPresent()) {
            throw new IllegalArgumentException("author id entered is invalid");
        }
        Author result = author.get();
        return result;
    }
    public List<String> authorBookList(Integer authorId) throws AuthorNotFoundException {
        List<String> bookNameList = new ArrayList<>();
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (!authorOptional.isPresent()) {
            throw new AuthorNotFoundException("author id is invalid");
        }
        Author author = authorOptional.get();
        List<Book> bookList = author.getBookList();
        for (Book book: bookList) {
            bookNameList.add(book.getBookName());
        }
        return bookNameList;
    }
}

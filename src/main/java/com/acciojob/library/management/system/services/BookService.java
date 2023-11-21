package com.acciojob.library.management.system.services;

import com.acciojob.library.management.system.entitys.Author;
import com.acciojob.library.management.system.entitys.Book;
import com.acciojob.library.management.system.enums.Genre;
import com.acciojob.library.management.system.exceptions.AuthorNotFoundException;
import com.acciojob.library.management.system.repositorys.AuthorRepository;
import com.acciojob.library.management.system.repositorys.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public String addBook(Book book, Integer authorId) throws Exception {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (!authorOptional.isPresent()) {
            throw new AuthorNotFoundException("author id entered is invalid");
        }
        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBookList().add(book);
        authorRepository.save(author);
        return "book has been added to the db";

    }
    public List<String> getBooksByGenre(Genre genre) {
        List<String> booksNameListByGenre = new ArrayList<>();
        List<Book> booksList = bookRepository.findBooksListByGenre(genre);
        for (Book book: booksList) {
            booksNameListByGenre.add(book.getBookName());
        }
        return booksNameListByGenre;
    }
}

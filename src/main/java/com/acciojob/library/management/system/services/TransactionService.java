package com.acciojob.library.management.system.services;

import com.acciojob.library.management.system.entitys.Book;
import com.acciojob.library.management.system.entitys.LibraryCard;
import com.acciojob.library.management.system.entitys.Transaction;
import com.acciojob.library.management.system.enums.CardStatus;
import com.acciojob.library.management.system.enums.TransactionStatus;
import com.acciojob.library.management.system.exceptions.*;
import com.acciojob.library.management.system.repositorys.BookRepository;
import com.acciojob.library.management.system.repositorys.CardRepository;
import com.acciojob.library.management.system.repositorys.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CardRepository cardRepository;
    private static final Integer MAX_LIMIT_OF_BOOK = 3;
    public String issueBook(Integer bookId, Integer cardNo) throws Exception {
        Transaction transaction = new Transaction();

//        valid card no
        Optional<LibraryCard> libraryCardOptional = cardRepository.findById(cardNo);
        if (!libraryCardOptional.isPresent()) {
            throw new CardNotFoundException("card no enter is invalid");
        }
        LibraryCard libraryCard = libraryCardOptional.get();

//        valid book id
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException("book id enter is invalid");
        }
        Book book = bookOptional.get();

//        availability of book
        if (book.isAvailable()) {
            throw new BookNotAvailableException("book is unavailable");
        }

//        valid card status
        if (!libraryCard.getCardStatus().equals(CardStatus.ACTIVE)) {
            throw new InvalidCardStatusException("card status is not active");
        }

//        max no of book issue
        if (libraryCard.getNoOfBookIssued() == MAX_LIMIT_OF_BOOK) {
            throw new MaxBookAlreadyIssuedException("max limit book reached");
        }
        transaction.setTransactionStatus(TransactionStatus.ISSUED);
        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued() + 1);
        book.setAvailable(false);

//        fk setting
        transaction.setLibraryCard(libraryCard);
        transaction.setBook(book);

//        saving relevant entities
        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);

//        save the parent entities
//        you can save child entity depend on condition
        transactionRepository.save(transaction);
        return "the book with book id: " + bookId + " has been issued to card no: " + cardNo;
    }
}

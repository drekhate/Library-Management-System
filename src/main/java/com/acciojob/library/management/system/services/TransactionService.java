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

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CardRepository cardRepository;
    private static final Integer MAX_LIMIT_OF_BOOK = 3;
    private static final Integer FINE_PER_DAY = 5;
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
    public String returnBook(Integer bookId, Integer cardNo) throws Exception {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new BookNotFoundException("book id enter is invalid");
        }
        Book book = bookOptional.get();
        Optional<LibraryCard> libraryCardOptional = cardRepository.findById(cardNo);
        if (!libraryCardOptional.isPresent()) {
            throw new CardNotFoundException("card no is invalid");
        }
        LibraryCard libraryCard = libraryCardOptional.get();
        Transaction transaction = transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatus(book, libraryCard, TransactionStatus.ISSUED);
        Date issueDate = transaction.getCreatedOn();
        long milliSeconds = Math.abs(System.currentTimeMillis() - issueDate.getTime());
        long days = TimeUnit.DAYS.convert(milliSeconds, TimeUnit.MILLISECONDS);
        int fineAmount = 0;
        if(days > 15) {
            fineAmount = (int) ((days - 15) * FINE_PER_DAY);
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        newTransaction.setReturnDate(new Date());
        newTransaction.setFine(fineAmount);

//        set fk
        newTransaction.setBook(book);
        newTransaction.setLibraryCard(libraryCard);

        book.setAvailable(true);
        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued() - 1);

        book.getTransactionList().add(transaction);
        libraryCard.getTransactionList().add(transaction);
        transactionRepository.save(newTransaction);
        return  "book has been returned";
    }
}

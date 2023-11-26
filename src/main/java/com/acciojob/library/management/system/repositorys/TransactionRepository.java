package com.acciojob.library.management.system.repositorys;

import com.acciojob.library.management.system.entitys.Book;
import com.acciojob.library.management.system.entitys.LibraryCard;
import com.acciojob.library.management.system.entitys.Transaction;
import com.acciojob.library.management.system.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTransactionByBookAndLibraryCardAndTransactionStatus(Book book, LibraryCard libraryCard, TransactionStatus transactionStatus);
}

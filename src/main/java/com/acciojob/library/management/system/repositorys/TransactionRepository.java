package com.acciojob.library.management.system.repositorys;

import com.acciojob.library.management.system.entitys.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}

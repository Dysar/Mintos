package com.example.mintos.repository.transaction;

import com.example.mintos.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountIDOrDestinationAccountIDEquals(Long sourceAccountID, Long destinationAccountID);
}

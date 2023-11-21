package com.example.mintos.repository.transaction;

import com.example.mintos.model.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findBySourceAccountIDOrDestinationAccountIDEqualsOrderByTimestampDesc(
            Long sourceAccountID, Long destinationAccountID, Pageable pageable);
}

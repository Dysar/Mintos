package com.example.mintos.repository.transaction;

import com.example.mintos.model.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Transaction} entities in the database.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Retrieves a page of transactions based on the source or destination account ID,
     * ordered by timestamp in descending order.
     *
     * @param sourceAccountID      The ID of the source account
     * @param destinationAccountID The ID of the destination account
     * @param pageable             The pagination information
     * @return Page of transactions matching the criteria
     */
    Page<Transaction> findBySourceAccountIDOrDestinationAccountIDEqualsOrderByTimestampDesc(
            Long sourceAccountID, Long destinationAccountID, Pageable pageable);
}

package com.example.mintos.repository.account;

import com.example.mintos.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for managing {@link Account} entities in the database.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

   /**
    * Retrieves a list of accounts based on the client ID.
    *
    * @param clientID The client ID to search for
    * @return List of accounts matching the client ID
    */
   List<Account> findByClientIDEquals(Long clientID);

   /**
    * Method to update the balance by increasing it.
    *
    * @param accountId   The ID of the account to update
    * @param newBalance  The new balance to set
    * @return The number of affected rows
    */
   @Transactional
   @Modifying
   @Query("UPDATE Account a SET a.balance = a.balance + :newBalance WHERE a.id = :accountId")
   int increaseBalance(@Param("accountId") Long accountId, @Param("newBalance") BigDecimal newBalance);

   /**
    * Method to update the balance by decreasing it.
    *
    * @param accountId   The ID of the account to update
    * @param newBalance  The new balance to set
    * @return The number of affected rows
    */
   @Transactional
   @Modifying
   @Query("UPDATE Account a SET a.balance = a.balance - :newBalance WHERE a.id = :accountId")
   int decreaseBalance(@Param("accountId") Long accountId, @Param("newBalance") BigDecimal newBalance);
}
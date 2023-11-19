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

// Spring Data JPA creates CRUD implementation at runtime automatically.
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

   List<Account> findByClientIDEquals(Long clientID);

   // Method to update the balance
   @Transactional
   @Modifying
   @Query("UPDATE Account a SET a.balance = a.balance + :newBalance WHERE a.id = :accountId")
   int increaseBalance(@Param("accountId") Long accountId, @Param("newBalance") BigDecimal newBalance);

   @Transactional
   @Modifying
   @Query("UPDATE Account a SET a.balance = a.balance - :newBalance WHERE a.id = :accountId")
   int decreaseBalance(@Param("accountId") Long accountId, @Param("newBalance") BigDecimal newBalance);
}
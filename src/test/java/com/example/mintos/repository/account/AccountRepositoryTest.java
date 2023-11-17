package com.example.mintos.repository.account;

import com.example.mintos.model.account.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindByClientIDEquals() {
        // Given
        Long clientID = 1L;

        // Create test data and save it to the database
        Account account1 = new Account(2L, 1L, BigDecimal.valueOf(13), "USD");
        Account account2 = new Account(2L, 2L, BigDecimal.valueOf(13), "EUR");
        accountRepository.save(account1);
        accountRepository.save(account2);

        // When
        List<Account> result = accountRepository.findByClientIDEquals(clientID);

        // Then
        assertEquals(1, result.size()); // Assuming both accounts have the same clientID
    }
}

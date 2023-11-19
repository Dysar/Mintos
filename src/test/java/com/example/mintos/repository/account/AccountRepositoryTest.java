package com.example.mintos.repository.account;

import com.example.mintos.model.account.Account;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//@Transactional
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

    @MockBean
    private ModelMapper modelMapper;

//    @Test
//    void increaseBalance_SuccessfulUpdate() {
//        // Mock data
//        Long accountId = 1L;
//        BigDecimal currentBalance = BigDecimal.valueOf(100);
//        BigDecimal newBalance = BigDecimal.valueOf(50);
//
//        // Create an account
//        Account account = new Account(accountId, 1L, currentBalance, "USD");
//        accountRepository.save(account);
//
//        // Call the method
//        int updatedRows = accountRepository.increaseBalance(accountId, newBalance);
//
//        // Verify the number of updated rows
//        assertEquals(1, updatedRows);
//
//        // Retrieve the updated account from the repository
//        Optional<Account> updatedAccount = accountRepository.findById(accountId);
//
//        // Check that the balance has been increased
//        assertTrue(updatedAccount.isPresent(), "Account not found after update");
//        assertEquals(currentBalance.add(newBalance), updatedAccount.get().getBalance(),
//                "Balance should be increased by the specified amount");
//    }
//
//    @Test
//    void decreaseBalance_SuccessfulUpdate() {
//        // Mock data
//        Long accountId = 1L;
//        BigDecimal currentBalance = BigDecimal.valueOf(100);
//        BigDecimal newBalance = BigDecimal.valueOf(30);
//
//        // Create an account
//        Account account = new Account(accountId, 1L, currentBalance, "USD");
//        accountRepository.save(account);
//
//        // Call the method
//        int updatedRows = accountRepository.decreaseBalance(accountId, newBalance);
//
//        // Verify the number of updated rows
//        assertEquals(1, updatedRows);
//
//        // Retrieve the updated account from the repository
//        Optional<Account> updatedAccount = accountRepository.findById(accountId);
//
//        // Check that the balance has been decreased
//        assertTrue(updatedAccount.isPresent(), "Account not found after update");
//        assertEquals(currentBalance.subtract(newBalance), updatedAccount.get().getBalance(),
//                "Balance should be decreased by the specified amount");
//    }
}

package com.example.mintos.service.transaction;

import com.example.mintos.model.account.Account;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.repository.account.AccountRepository;
import com.example.mintos.repository.transaction.TransactionRepository;
import com.example.mintos.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void findBySourceAccountIDOrDestinationAccountIDEquals() {
        // Arrange
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, 101L, 102L, LocalDateTime.of(2023, 11, 19, 12, 30), "USD", new BigDecimal("500.00")));

        transactions.add(new Transaction(2L, 201L, 202L, LocalDateTime.of(2023, 11, 20, 10, 45), "EUR", new BigDecimal("750.50")));


        Mockito.when(transactionRepository.
                findBySourceAccountIDOrDestinationAccountIDEquals(1L,1L))
                .thenReturn(transactions);

        // Act
        List<Transaction> result = transactionService.findBySourceAccountIDOrDestinationAccountIDEquals(1L);

        // Assert
        assertEquals(transactions, result);
    }
}

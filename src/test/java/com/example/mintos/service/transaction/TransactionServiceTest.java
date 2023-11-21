package com.example.mintos.service.transaction;

import com.example.mintos.exception.CurrencyRateNotFoundException;
import com.example.mintos.exception.NegativeBalanceException;
import com.example.mintos.model.account.Account;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.repository.account.AccountRepository;
import com.example.mintos.repository.transaction.TransactionRepository;
import com.example.mintos.service.exchange.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ExchangeRateService exchangeRateService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void findBySourceAccountIDOrDestinationAccountIDEquals() {
        // Arrange
        Pageable pageable = Pageable.unpaged();

        Page<Transaction> expectedPage = new PageImpl<>(Collections.emptyList(), pageable, 0);



        Mockito.when(transactionRepository.findBySourceAccountIDOrDestinationAccountIDEqualsOrderByTimestampDesc(1L,1L,pageable))
                .thenReturn(expectedPage);

        // Act
        Page<Transaction> result = transactionService.findBySourceAccountIDOrDestinationAccountIDEquals(1L, pageable);

        // Assert
        assertEquals(expectedPage, result);
    }

    @Test
    public void testCreateTransaction() throws AccountNotFoundException, NegativeBalanceException, CurrencyRateNotFoundException {
        // Given
        Long sourceAccountId = 1L;
        Long destinationAccountId = 2L;
        BigDecimal amount = BigDecimal.TEN;

        Account sourceAccount = new Account();
        sourceAccount.setId(sourceAccountId);
        sourceAccount.setBalance(BigDecimal.valueOf(100)); // Set a positive balance for testing

        Account destinationAccount = new Account();
        destinationAccount.setId(destinationAccountId);
        destinationAccount.setCurrencyCode("USD");

        when(accountRepository.findById(sourceAccountId)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(destinationAccountId)).thenReturn(Optional.of(destinationAccount));
        when(exchangeRateService.getExchangeRate("USD")).thenReturn(BigDecimal.valueOf(1.2)); // Set a dummy exchange rate

        // When
        transactionService.createTransaction(sourceAccountId, destinationAccountId, amount);

        // Then
        verify(accountRepository, times(1)).decreaseBalance(sourceAccountId, amount);
        verify(accountRepository, times(1)).increaseBalance(destinationAccountId, amount.multiply(BigDecimal.valueOf(1.2)));
        verify(transactionRepository, times(1)).save(any(Transaction.class));

    }
}

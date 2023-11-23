package com.example.mintos.service.account;

import com.example.mintos.model.account.Account;
import com.example.mintos.repository.account.AccountRepository;
import com.example.mintos.service.account.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testFindAll() {
        // Arrange
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(2L, 1L, BigDecimal.valueOf(13), "USD"));
        accounts.add(new Account(1L, 1L, BigDecimal.valueOf(11), "USD"));

        Mockito.when(repository.findAll()).thenReturn(accounts);

        // Act
        List<Account> result = accountService.findAll();

        // Assert
        assertEquals(accounts, result);
    }

    @Test
    public void testFindByClientIDEquals() {
        // Arrange
        Long clientID = 1L;
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(2L, 1L, BigDecimal.valueOf(13), "USD"));
        accounts.add(new Account(1L, 1L, BigDecimal.valueOf(11), "USD"));
        accounts.add(new Account(1L, 2L, BigDecimal.valueOf(11), "USD"));

        Mockito.when(repository.findByClientIDEquals(clientID)).thenReturn(accounts);

        // Act
        List<Account> result = accountService.findByClientIDEquals(clientID);

        // Assert
        assertEquals(accounts, result);
    }
}

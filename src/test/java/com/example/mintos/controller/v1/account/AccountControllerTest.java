package com.example.mintos.controller.v1.account;

import com.example.mintos.dto.model.account.AccountDTO;
import com.example.mintos.dto.response.Response;
import com.example.mintos.model.account.Account;
import com.example.mintos.service.account.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Given
        List<Account> accounts = new ArrayList<>();
        when(accountService.findAll()).thenReturn(accounts);

        // When
        Response<List<AccountDTO>> response = accountController.findAll();

        // Then
        Assertions.assertNotNull(response);
        verify(accountService, times(1)).findAll();
    }

    @Test
    void testFindByClientIDEquals() {
        // Given
        Long clientID = 1L;
        List<Account> expectedAccounts = new ArrayList<>();
        when(accountService.findByClientIDEquals(clientID)).thenReturn(expectedAccounts);

        // When
        List<Account> result = accountController.findByClientIDEquals(clientID);

        // Then
        Assertions.assertNotNull(result);
        verify(accountService, times(1)).findByClientIDEquals(clientID);
    }
}

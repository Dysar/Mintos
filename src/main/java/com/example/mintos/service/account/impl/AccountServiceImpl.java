package com.example.mintos.service.account.impl;

import com.example.mintos.repository.account.AccountRepository;
import com.example.mintos.model.account.Account;
import com.example.mintos.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all accounts from the repository.
     *
     * @return List of all accounts
     */
    public List<Account> findAll() {
        return repository.findAll();
    }

    /**
     * Retrieves accounts based on the provided client ID.
     *
     * @param clientID The client ID to filter accounts
     * @return List of accounts matching the client ID
     */
    public List<Account> findByClientIDEquals(Long clientID) {
        return repository.findByClientIDEquals(clientID);
    }
}

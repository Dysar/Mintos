package com.example.mintos.service.account;

import com.example.mintos.repository.account.AccountRepository;
import com.example.mintos.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
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

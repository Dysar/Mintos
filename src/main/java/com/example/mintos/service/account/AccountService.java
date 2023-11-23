package com.example.mintos.service.account;

import com.example.mintos.model.account.Account;

import java.util.List;

public interface AccountService {

    /**
     * Retrieves all accounts from the repository.
     *
     * @return List of all accounts
     */
    List<Account> findAll();

    /**
     * Retrieves accounts based on the provided client ID.
     *
     * @param clientID The client ID to filter accounts
     * @return List of accounts matching the client ID
     */
    List<Account> findByClientIDEquals(Long clientID);
}


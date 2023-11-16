package com.example.mintos.service.account;

import com.example.mintos.model.account.Account;

import java.util.List;

/**
 * Interface that provides methods for manipulating Account objects.
 *
 * @author Mariana Azevedo
 * @since 25/10/2020
 */
public interface AccountService {
    List<Account> findAccountsByClientID(Long clientID);
}

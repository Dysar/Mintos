package com.example.mintos.service.account.impl;

import com.example.mintos.model.account.Account;
import com.example.mintos.repository.AccountRepository;
import com.example.mintos.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public List<Account> findAccountsByClientID(Long clientID) {
        return repository.findAccountsByClientID(clientID);
    }


}

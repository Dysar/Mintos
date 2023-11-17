package com.example.mintos.service.account;

import com.example.mintos.repository.account.AccountRepository;
import com.example.mintos.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }

    public Account save(Account book) {
        return repository.save(book);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Account> findByClientIDEquals(Long clientID) {
        return repository.findByClientIDEquals(clientID);
    }
}

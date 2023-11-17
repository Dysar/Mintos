package com.example.mintos.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Account> findById(@PathVariable Long id) {
        return accountService.findById(id);
    }


    @GetMapping("/find/clientID/{clientID}")
    public List<Account> findByClientIDEquals(
            @PathVariable Long clientID) {
        return accountService.findByClientIDEquals(clientID);
    }

}

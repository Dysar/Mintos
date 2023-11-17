package com.example.mintos.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public Response<List<AccountDTO>> findAll() {
        Response<List<AccountDTO>> response = new Response<>();
        List<Account> accounts = accountService.findAll();
        List<AccountDTO> accountDTO = new ArrayList<>();

        accounts.forEach(t -> accountDTO.add(t.convertEntityToDTO()));


        response.setData(accountDTO);
        return response;
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

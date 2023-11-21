package com.example.mintos.controller.v1.account;


import com.example.mintos.dto.model.account.AccountDTO;
import com.example.mintos.dto.response.Response;
import com.example.mintos.model.account.Account;
import com.example.mintos.service.account.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService transactionService) {
        this.accountService = transactionService;
    }

    @GetMapping
    @ApiOperation(value = "Route to find all accounts in the API")
    public Response<List<AccountDTO>> findAll() {
        Response<List<AccountDTO>> response = new Response<>();
        List<Account> accounts = accountService.findAll();
        List<AccountDTO> accountDTO = new ArrayList<>();

        accounts.forEach(t -> accountDTO.add(t.convertEntityToDTO()));

        response.setData(accountDTO);
        return response;
    }


    @GetMapping("/find/clientID/{clientID}")
    public List<Account> findByClientIDEquals(
            @PathVariable Long clientID) {
        return accountService.findByClientIDEquals(clientID);
    }

}

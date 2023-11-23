package com.example.mintos.controller.v1.account;


import com.example.mintos.dto.model.account.AccountDTO;
import com.example.mintos.dto.response.Response;
import com.example.mintos.model.account.Account;
import com.example.mintos.service.account.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Retrieve all accounts in the API.
     *
     * @return Response containing a list of AccountDTOs
     */
    @GetMapping
    @ApiOperation(value = "Route to find all accounts in the API")
    public Response<List<AccountDTO>> findAll() {
        Response<List<AccountDTO>> response = new Response<>();
        List<Account> accounts = accountService.findAll();
        List<AccountDTO> accountDTO = new ArrayList<>();

        // Convert each Account entity to DTO
        accounts.forEach(t -> accountDTO.add(t.convertEntityToDTO()));

        response.setData(accountDTO);
        return response;
    }

    /**
     * Retrieve accounts by client ID.
     *
     * @param clientID The client ID to filter accounts
     * @return List of accounts matching the client ID
     */
    @GetMapping("/find/clientID/{clientID}")
    public List<Account> findByClientIDEquals(
            @PathVariable Long clientID) {
        return accountService.findByClientIDEquals(clientID);
    }

}

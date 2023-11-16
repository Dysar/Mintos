package com.example.mintos.controller.v1;

import com.example.mintos.dto.model.account.AccountDTO;
import com.example.mintos.model.account.Account;
import com.example.mintos.service.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.mintos.dto.response.Response;

import javax.security.auth.login.AccountNotFoundException;

/**
 * SpringBoot RestController that creates all service end-points related to the account.
 *
 * @author Mariana Azevedo
 * @since 31/10/2020
 */

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    AccountService accountService;

    @GetMapping(value = "/byClientID/{id}")
    public ResponseEntity<Response<List<AccountDTO>>> findByAccountNumber(@PathVariable("id") Long id) throws AccountNotFoundException {

        Response<List<AccountDTO>> response = new Response<>();
        List<Account> accounts = accountService.findAccountsByClientID(id);

        List<AccountDTO> accountDTO = new ArrayList<>();
        accounts.stream().forEach(t -> accountDTO.add(t.convertEntityToDTO()));

//        accountDTO.stream().forEach(dto -> {
//            try {
//                createSelfLinkInCollections(apiVersion, apiKey, dto);
//            } catch (AccountNotFoundException e) {
//                log.error("There are no accounts registered with the accountNumber= {}", accountNumber);
//            }
//        });

        response.setData(accountDTO);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//        headers.add(TravelsApiUtil.HEADER_TRAVELS_API_VERSION, apiVersion);
//        headers.add(TravelsApiUtil.HEADER_API_KEY, apiKey);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}

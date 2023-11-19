package com.example.mintos.controller.v1.transaction;

import com.example.mintos.exception.NegativeBalanceException;
import com.example.mintos.service.transaction.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(
            @RequestParam Long sourceAccountId,
            @RequestParam Long destinationAccountId,
            @RequestParam BigDecimal amount) {

        try {
            transactionService.createTransaction(sourceAccountId, destinationAccountId, amount);
            return ResponseEntity.created(null).body("Transaction created successfully");
        } catch (AccountNotFoundException | NegativeBalanceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

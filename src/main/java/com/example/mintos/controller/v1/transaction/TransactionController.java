package com.example.mintos.controller.v1.transaction;

import com.example.mintos.dto.model.transaction.TransactionDTO;
import com.example.mintos.dto.response.Response;
import com.example.mintos.exception.CurrencyRateNotFoundException;
import com.example.mintos.exception.NegativeBalanceException;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(
            @RequestParam Long sourceAccountId,
            @RequestParam Long destinationAccountId,
            @RequestParam BigDecimal amount) {

        try {
            transactionService.createTransaction(sourceAccountId, destinationAccountId, amount);
            return ResponseEntity.created(null).body("Transaction created successfully");
        } catch (AccountNotFoundException | NegativeBalanceException | CurrencyRateNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/history/{accountID}")
    public ResponseEntity<Response<List<TransactionDTO>>> getTransactionHistory(@PathVariable Long accountID) {
        Response<List<TransactionDTO>> response = new Response<>();
        try {
            List<Transaction> transactionHistory = transactionService.findBySourceAccountIDOrDestinationAccountIDEquals(accountID);
            List<TransactionDTO> transactionHistoryDTO = new ArrayList<>();

            transactionHistory.forEach(t -> transactionHistoryDTO.add(t.convertEntityToDTO()));
            response.setData(transactionHistoryDTO);
            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            response.addErrorMsgToResponse(e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}

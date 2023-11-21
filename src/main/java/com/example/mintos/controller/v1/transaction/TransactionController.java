package com.example.mintos.controller.v1.transaction;

import com.example.mintos.dto.model.transaction.TransactionDTO;
import com.example.mintos.dto.response.Response;
import com.example.mintos.exception.CurrencyRateNotFoundException;
import com.example.mintos.exception.NegativeBalanceException;
import com.example.mintos.model.transaction.Transaction;
import com.example.mintos.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    /**
     * Create a new transaction.
     *
     * @param sourceAccountId      The ID of the source account
     * @param destinationAccountId The ID of the destination account
     * @param amount               The transaction amount
     * @return ResponseEntity with a success or error message
     */
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

    /**
     * Get transaction history for a specific account.
     *
     * @param accountID The ID of the account
     * @param offset    The offset for paging
     * @param limit     The limit for paging
     * @return ResponseEntity with a list of TransactionDTOs or an error message
     */
    @GetMapping("/history/{accountID}")
    public ResponseEntity<Response<List<TransactionDTO>>> getTransactionHistory(
            @PathVariable Long accountID,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        Response<List<TransactionDTO>> response = new Response<>();
        try {
            Pageable pageable = PageRequest.of(offset / limit, limit);
            Page<Transaction> transactionHistory = transactionService.findBySourceAccountIDOrDestinationAccountIDEquals(accountID, pageable);
            List<TransactionDTO> transactionHistoryDTO = new ArrayList<>();
            transactionHistory.stream().forEach(t -> transactionHistoryDTO.add(t.convertEntityToDTO()));

            response.setData(transactionHistoryDTO);
            return ResponseEntity.status(200).body(response);
        } catch (RuntimeException e) {
            response.addErrorMsgToResponse(e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}

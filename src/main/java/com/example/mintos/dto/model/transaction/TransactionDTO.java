package com.example.mintos.dto.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO extends RepresentationModel<TransactionDTO> {

    private Long transactionId;
    private Long sourceAccountID;
    private Long destinationAccountID;
    private LocalDateTime timestamp;
    private String currencyCode;
    private BigDecimal amount;
}

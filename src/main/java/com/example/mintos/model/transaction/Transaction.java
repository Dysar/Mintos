package com.example.mintos.model.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = -6762432601286928295L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private Long sourceAccountID;
    private Long destinationAccountID;
    private LocalDateTime timestamp;
    private String currencyCode;
    private BigDecimal amount;
}

package com.example.mintos.model.exchange;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_rate")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "currency_code")
    private String currencyCode;
    @Column(name = "last_modified")
    private LocalDateTime lastModified;
}

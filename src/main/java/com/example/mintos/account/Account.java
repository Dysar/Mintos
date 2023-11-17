package com.example.mintos.account;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long clientID;
//    private LocalDate publishDate;

    // for JPA only, no use
    public Account() {
    }

    // getters, setters and constructor
}

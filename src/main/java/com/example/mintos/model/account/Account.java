package com.example.mintos.model.account;

import com.example.mintos.dto.model.account.AccountDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Currency;

/**
 * Class that implements the Account structure.
 *
 * @author David Zingerman
 * @since 16/11/2023
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "balance")
    private double balance;

    @Column(name = "currency")
    private Currency currency;

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Account(Long id) {
        this.id = id;
    }


    public AccountDTO convertEntityToDTO() {
        return new ModelMapper().map(this, AccountDTO.class);
    }
}

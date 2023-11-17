package com.example.mintos.model.account;

import com.example.mintos.dto.model.account.AccountDTO;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = -6762432601286928295L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "client_id")
    private Long clientID;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "currency_code")
    private String currencyCode;

    public AccountDTO convertEntityToDTO() {
        return new ModelMapper().map(this, AccountDTO.class);
    }
}

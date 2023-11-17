package com.example.mintos.dto.model.account;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO extends RepresentationModel<AccountDTO> {
    private Long id;
    private Long clientID;
    private BigDecimal balance;
    private String currencyCode;
}

package com.example.mintos.dto.model.account;

import com.example.mintos.model.account.Account;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.util.Currency;

public class AccountDTO extends RepresentationModel<AccountDTO> {

	private Long id;

	private double balance;

	private Currency currency;

	public Account convertDTOToEntity() {
		return new ModelMapper().map(this, Account.class);
	}
}

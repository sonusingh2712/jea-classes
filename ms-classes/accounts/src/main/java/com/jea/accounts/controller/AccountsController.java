package com.jea.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jea.accounts.dto.CustomerDetailsDto;
import com.jea.accounts.service.AccountsService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

	@Autowired
	private AccountsService accountsService;
	
	// create an account
	@PostMapping
	public CustomerDetailsDto createAccount(@RequestBody CustomerDetailsDto customerDetailsDto) {
		return accountsService.createAccount(customerDetailsDto);
	}
}

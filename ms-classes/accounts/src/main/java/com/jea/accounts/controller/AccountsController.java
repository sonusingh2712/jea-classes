package com.jea.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// Get Account Details
	@GetMapping("/{mobileNumber}")
	public CustomerDetailsDto getAccountDetails(@PathVariable String mobileNumber) {
		return accountsService.getAccountDetails(mobileNumber);
	}
	
	// Update account details
	@PutMapping()
	public boolean updateAccountInformation(@RequestBody CustomerDetailsDto customerDetailsDto) {
		return accountsService.updateAccountInformation(customerDetailsDto);
	}
	
	// Delete account and customer details
	@DeleteMapping("/{mobileNumber}")
	public boolean deleteAccountInformation(@PathVariable String mobileNumber) {
		return accountsService.deleteAccountInformation(mobileNumber);
	}
}

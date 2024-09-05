package com.jea.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {
	
	private Long accountNumber;// back-end will generate 10-digit random account number
	private Long customerId;
	private String accountType;
	private String branchAddress;
}

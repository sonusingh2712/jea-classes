package com.jea.accounts.dto;

import lombok.Data;

@Data
public class CustomerDetailsDto {
	
	private Long customerId;
	private String name;
	private String email;
	private String mobileNumber;
	private AccountsDto accountsDto;
}

package com.jea.accounts.service;

import com.jea.accounts.dto.CustomerDetailsDto;

public interface AccountsService {
	
	CustomerDetailsDto createAccount(CustomerDetailsDto customerDetailsDto);

	CustomerDetailsDto getAccountDetails(String mobileNumber);

	boolean updateAccountInformation(CustomerDetailsDto customerDetailsDto);

	boolean deleteAccountInformation(String mobileNumber);
	
}

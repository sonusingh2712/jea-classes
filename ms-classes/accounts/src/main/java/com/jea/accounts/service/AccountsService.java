package com.jea.accounts.service;

import com.jea.accounts.dto.CustomerDetailsDto;

public interface AccountsService {
	
	CustomerDetailsDto createAccount(CustomerDetailsDto customerDetailsDto);
	
}

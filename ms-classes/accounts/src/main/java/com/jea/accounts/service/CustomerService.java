package com.jea.accounts.service;

import com.jea.accounts.dto.CustomerDetailsDto;

public interface CustomerService {
	CustomerDetailsDto getCustomerDetails(String mobileNumber);
}

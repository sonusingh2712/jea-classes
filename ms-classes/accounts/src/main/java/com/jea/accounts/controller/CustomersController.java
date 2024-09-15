package com.jea.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jea.accounts.dto.CustomerDetailsDto;
import com.jea.accounts.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/customers")
public class CustomersController {
	
	
	@Autowired
	private CustomerService customersService; 
	
	@PostMapping("/fetchCustomerDetails")
	public CustomerDetailsDto fetchCustomerDetails(@RequestParam String mobileNumber) {
		log.info(" ::: Fetching Customer Details ::: ");
		return customersService.getCustomerDetails(mobileNumber);
	}
	
}

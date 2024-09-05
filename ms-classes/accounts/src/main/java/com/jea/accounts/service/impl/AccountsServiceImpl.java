package com.jea.accounts.service.impl;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jea.accounts.dto.AccountsDto;
import com.jea.accounts.dto.CustomerDetailsDto;
import com.jea.accounts.entities.Accounts;
import com.jea.accounts.entities.Customer;
import com.jea.accounts.repository.AccountsRepository;
import com.jea.accounts.repository.CustomerRepository;
import com.jea.accounts.service.AccountsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountsServiceImpl implements AccountsService {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public CustomerDetailsDto createAccount(CustomerDetailsDto customerDetailsDto) {
		
		Customer customerEntity = new Customer();
		BeanUtils.copyProperties(customerDetailsDto, customerEntity);
		
		boolean isExistingCustomer = customerRepository.findByMobileNumber(customerDetailsDto.getMobileNumber()).isPresent();
		
		if(isExistingCustomer) {
			throw new RuntimeException(customerDetailsDto.getMobileNumber()+ " : Mobile number is already associated with another user.");
		}
		
		Customer savedCustomer = customerRepository.save(customerEntity);
		BeanUtils.copyProperties(savedCustomer, customerDetailsDto);
		
		Accounts savedAccounts = createNewAccount(savedCustomer);
		AccountsDto accountsDto = new AccountsDto();
		
		BeanUtils.copyProperties(savedAccounts, accountsDto);
		customerDetailsDto.setAccountsDto(accountsDto);
		
		return customerDetailsDto;
	}
	
	private Accounts createNewAccount(Customer customer) {
		Accounts accounts = new Accounts();
		long accountNumber = 100000000 + new Random().nextInt(9999999);
		accounts.setAccountNumber(accountNumber);
		accounts.setCustomerId(customer.getCustomerId());
		accounts.setAccountType("Savings");
		accounts.setBranchAddress("Bangalore");
		accounts = accountsRepository.save(accounts);
		return accounts;
	}

}

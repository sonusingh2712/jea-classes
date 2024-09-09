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

import jakarta.persistence.EntityNotFoundException;
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

	@Override
	public CustomerDetailsDto getAccountDetails(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new EntityNotFoundException("No any customer is available with mobile number ::"+mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new EntityNotFoundException("No any accounts found with mobile number ::"+mobileNumber));
		
		AccountsDto accountsDto = new AccountsDto();
		BeanUtils.copyProperties(accounts, accountsDto);
		
		CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
		BeanUtils.copyProperties(customer, customerDetailsDto);
		
		customerDetailsDto.setAccountsDto(accountsDto);
		
		return customerDetailsDto;
	}

	@Override
	public boolean updateAccountInformation(CustomerDetailsDto customerDetailsDto) {
		var accountsDto = customerDetailsDto.getAccountsDto();
		if(accountsDto == null) {
			throw new IllegalArgumentException("Account details are missing.");
		}
		Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new EntityNotFoundException("Account not found."));
		BeanUtils.copyProperties(customerDetailsDto, accounts);
		accounts = accountsRepository.save(accounts);
		
		Customer savedCustomer = customerRepository.findById(accounts.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("Customer Not Found."));
		BeanUtils.copyProperties(customerDetailsDto, savedCustomer);
		savedCustomer = customerRepository.save(savedCustomer);
		return true;
	}

	
	@Override
	public boolean deleteAccountInformation(String mobileNumber) {
		Customer savedCustomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new EntityNotFoundException("Customer Not Found."));
//		Optional<Accounts> accounts = accountsRepository.findById(savedCustomer.getCustomerId());
//		accounts.ifPresent(savedData -> accountsRepository.delete(savedData));
/*		if(accounts.isPresent()) {
			accountsRepository.delete(accounts.get());
		}
*/
//		customerRepository.delete(savedCustomer);
		
		accountsRepository.deleteByCustomerId(savedCustomer.getCustomerId());
		customerRepository.deleteById(savedCustomer.getCustomerId());
		return true;
	}
}

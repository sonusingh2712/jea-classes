package com.jea.accounts.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jea.accounts.dto.AccountsDto;
import com.jea.accounts.dto.CardsDetailDto;
import com.jea.accounts.dto.CustomerDetailsDto;
import com.jea.accounts.dto.LoansDetailDto;
import com.jea.accounts.entities.Accounts;
import com.jea.accounts.entities.Customer;
import com.jea.accounts.feignclients.CardsFeignClients;
import com.jea.accounts.feignclients.LoanFeignClients;
import com.jea.accounts.repository.AccountsRepository;
import com.jea.accounts.repository.CustomerRepository;
import com.jea.accounts.service.CustomerService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	
	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	private CardsFeignClients cardsFeignClients;
	private LoanFeignClients loanFeignClients;

	@Override
	public CustomerDetailsDto getCustomerDetails(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new EntityNotFoundException("No Customer Found associated with mobile number :::"+mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new EntityNotFoundException("No Account Found associated with customerId :::"+customer.getCustomerId()));
		List<LoansDetailDto> loansDetailDto = loanFeignClients.getAllLoansByMobileNumber(mobileNumber); 
		CardsDetailDto cardsDetailDto = cardsFeignClients.getCardsInfoByMobileNumber(mobileNumber);
		
		CustomerDetailsDto customerDetailsDto = setCustomersDetailsDto(customer, accounts, loansDetailDto, cardsDetailDto);
		
		return customerDetailsDto;
	}

	private CustomerDetailsDto setCustomersDetailsDto(Customer customer, Accounts accounts, List<LoansDetailDto> loansDetailDto, CardsDetailDto cardsDetailDto) {
		log.info("Setting customer details");
		
		CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
		BeanUtils.copyProperties(customer, customerDetailsDto);
		
		AccountsDto accountsDto = new AccountsDto();
		BeanUtils.copyProperties(accounts, accountsDto);
		customerDetailsDto.setAccountsDto(accountsDto);
		
		setCardsDetails(cardsDetailDto, customerDetailsDto);
		
		setLoanDetails(loansDetailDto, customerDetailsDto);
		return customerDetailsDto;
	}

	private void setLoanDetails(List<LoansDetailDto> loansDetailDto, CustomerDetailsDto customerDetailsDto) {
		log.info("Settings loan details");
		if(!loansDetailDto.isEmpty()) {
			customerDetailsDto.setLoansDetailsDto(loansDetailDto);
		}
	}

	private void setCardsDetails(CardsDetailDto cardsDetailDto, CustomerDetailsDto customerDetailsDto) {
		log.info("Settings card details");
		if(cardsDetailDto != null) {
			customerDetailsDto.setCardsDetailsDto(cardsDetailDto);
		}
	}
	
}

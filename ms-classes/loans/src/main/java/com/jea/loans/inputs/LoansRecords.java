package com.jea.loans.inputs;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jea.loans.dto.LoansDetailDto;
import com.jea.loans.service.LoansService;

import jakarta.annotation.PostConstruct;

@Component
public class LoansRecords {
	@Autowired
	private LoansService loansService;
	
	@PostConstruct
	public void createListOfLoans() {
		List<String> listOfMobileNumber = Stream.of("9999999999","9999988888","8888899999","8888888888").collect(Collectors.toList());
		
		List<LoansDetailDto> loansDetailDtoList = listOfMobileNumber.stream()
				.map( mobileNumber -> loansService.createNewLoan(mobileNumber)).collect(Collectors.toList());
		
		System.out.println("Auto Generated Loans ::: ");
		System.out.println(loansDetailDtoList);
	}
}

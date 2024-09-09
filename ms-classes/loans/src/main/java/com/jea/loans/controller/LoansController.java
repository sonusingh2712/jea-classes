package com.jea.loans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jea.loans.dto.LoansDetailDto;
import com.jea.loans.service.LoansService;

@RestController
@RequestMapping("/api/v1/loans")
public class LoansController {
	
	@Autowired
	private LoansService loansService;
	
	@GetMapping
	public List<LoansDetailDto> getAllLoans(){
		return loansService.getAllLoans();
	}
	
	@GetMapping("/{mobileNumber}")
	public List<LoansDetailDto> getAllLoansDetail(@PathVariable String mobileNumber){
		return loansService.getAllLoansDetail(mobileNumber);
	}
	
	@GetMapping("/id/{loanId}")
	public LoansDetailDto getLoanById(@PathVariable Long loanId){
		return loansService.getLoanById(loanId);
	}
}

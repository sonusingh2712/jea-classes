package com.jea.loans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jea.loans.dto.LoansDetailDto;
import com.jea.loans.service.LoansService;

@RestController
@RequestMapping("/api/v1/loans")
public class LoansController {
	
	@Autowired
	private LoansService loansService;
	
	@PostMapping("/{mobileNumber}")
	public LoansDetailDto createNewLoan(@PathVariable String mobileNumber) {
		return loansService.createNewLoan(mobileNumber);
	}
	
	@GetMapping("/id/{loanId}")
	public LoansDetailDto getLoanById(@PathVariable Long loanId){
		return loansService.getLoanById(loanId);
	}
	
	@GetMapping
	public List<LoansDetailDto> getAllLoans(){
		return loansService.getAllLoans();
	}
	
	@GetMapping("/{mobileNumber}")
	public List<LoansDetailDto> getAllLoansByMobileNumber(@PathVariable String mobileNumber){
		return loansService.getAllLoansDetail(mobileNumber);
	}
	
	@PutMapping
	public boolean updateLoadDetails(LoansDetailDto loansDetailDto) {
		return loansService.updateLoanDetails(loansDetailDto);
	}
	
	@DeleteMapping("/{mobileNumber}")
	public void deleteLoanDetails(@PathVariable String mobileNumber) {
		loansService.deleteLoansDetails(mobileNumber);
	}
	
}

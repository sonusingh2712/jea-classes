package com.jea.loans.service;

import java.util.List;

import com.jea.loans.dto.LoansDetailDto;

public interface LoansService {
	
	LoansDetailDto createNewLoan(String mobileNumber);

	LoansDetailDto getLoanById(Long id);
	List<LoansDetailDto> getAllLoans();
	List<LoansDetailDto> getAllLoansDetail(String mobileNumber);

	boolean updateLoanDetails(LoansDetailDto loansDetailDto);
	void deleteLoansDetails(String mobileNumber);

}
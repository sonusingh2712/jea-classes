package com.jea.loans.service;

import java.util.List;

import com.jea.loans.dto.LoansDetailDto;

public interface LoansService {

	List<LoansDetailDto> getAllLoans();
	List<LoansDetailDto> getAllLoansDetail(String mobileNumber);
	LoansDetailDto getLoanById(Long id);

	
}

package com.jea.loans.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.jea.loans.dto.LoansDetailDto;
import com.jea.loans.entities.Loans;
import com.jea.loans.repository.LoansRepository;
import com.jea.loans.service.LoansService;
import jakarta.persistence.EntityNotFoundException;

import static com.jea.loans.util.LoansConstant.*;
@Repository
public class LoansServiceImpl implements LoansService {

	@Autowired
	private LoansRepository loansRepository;
	

	@Override
	public LoansDetailDto createNewLoan(String mobileNumber) {
		boolean checkExistingLoan = loansRepository.findByMobileNumber(mobileNumber).isPresent();
		if(checkExistingLoan) {
			throw new RuntimeException("Already a loan exists on mobile Number :: "+mobileNumber);
		}
		Loans loanDetails = loansRepository.save(setLoanDetails(mobileNumber));
		LoansDetailDto loansDetailDto = new LoansDetailDto();
		BeanUtils.copyProperties(loanDetails, loansDetailDto);
		return loansDetailDto;
	}


	private Loans setLoanDetails(String mobileNumber) {
		Loans loans = new Loans();
		loans.setMobileNumber(mobileNumber);
		loans.setLoanType(LOAN_TYPE);
		loans.setTotalLoan(MAX_LOAN_AMOUNT);
		loans.setAmountPaid(LOAN_AMOUNT_PAID);
		loans.setOutstandingAmount(MAX_LOAN_AMOUNT-LOAN_AMOUNT_PAID);
		return loans;
	}
	

	@Override
	public LoansDetailDto getLoanById(Long id) {
		Loans savedLoans = loansRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" No any loan found with id :: "+id));
		LoansDetailDto loansDetailDto = new LoansDetailDto();
		BeanUtils.copyProperties(savedLoans, loansDetailDto);
		return loansDetailDto;
	}
	
	
	@Override
	public List<LoansDetailDto> getAllLoans() {
		List<Loans> listOfLoansInDB = loansRepository.findAll();
		List<LoansDetailDto> listOfLoans = setDbRecordsToDetailsDto(listOfLoansInDB);
		return listOfLoans;
	}
	
	@Override
	public List<LoansDetailDto> getAllLoansDetail(String mobileNumber) {
		List<Loans> listOfLoansInDB = loansRepository.findAllByMobileNumber(mobileNumber);
		List<LoansDetailDto> listOfLoans = setDbRecordsToDetailsDto(listOfLoansInDB);
		return listOfLoans;
	}
	

	private List<LoansDetailDto> setDbRecordsToDetailsDto(List<Loans> listOfLoansInDB) {
		//If there is no any loan associated with mobileNumber
		if(ObjectUtils.isEmpty(listOfLoansInDB)) {
			return null;
		}
		
		List<LoansDetailDto> listOfLoans = new ArrayList<>();
		for(Loans savedLoans : listOfLoansInDB) {
			LoansDetailDto loansDetailDto = new LoansDetailDto();
			BeanUtils.copyProperties(savedLoans, loansDetailDto);
			listOfLoans.add(loansDetailDto);
		}
		return listOfLoans;
	}
}
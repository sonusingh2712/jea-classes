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

@Repository
public class LoansServiceImpl implements LoansService {

	@Autowired
	private LoansRepository loansRepository;
	
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
	
	
	@Override
	public LoansDetailDto getLoanById(Long id) {
		Loans savedLoans = loansRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(" No any loan found with id :: "+id));
		LoansDetailDto loansDetailDto = new LoansDetailDto();
		BeanUtils.copyProperties(savedLoans, loansDetailDto);
		return loansDetailDto;
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
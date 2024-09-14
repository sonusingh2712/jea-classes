package com.jea.loans.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.loans.entities.Loans;

public interface LoansRepository extends JpaRepository<Loans, Long> {
	
	Optional<Loans> findByMobileNumber(String mobileNumber);

	List<Loans> findAllByMobileNumber(String mobileNumber);

}

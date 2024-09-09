package com.jea.loans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.loans.entities.Loans;

public interface LoansRepository extends JpaRepository<Loans, Long> {

	List<Loans> findAllByMobileNumber(String mobileNumber);

}

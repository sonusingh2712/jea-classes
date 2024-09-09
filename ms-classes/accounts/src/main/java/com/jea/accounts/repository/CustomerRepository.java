package com.jea.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.accounts.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByMobileNumber(String mobileNumber);
	
}
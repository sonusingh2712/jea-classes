package com.jea.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.jea.accounts.entities.Accounts;

import jakarta.transaction.Transactional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

	Optional<Accounts> findByCustomerId(Long customerId);
	
	@Transactional
	@Modifying // optional to use
	void deleteByCustomerId(Long customerId);

}

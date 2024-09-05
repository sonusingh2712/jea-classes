package com.jea.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.accounts.entities.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}

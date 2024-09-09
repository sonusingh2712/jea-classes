package com.jea.accounts.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Accounts {
	
	@Id
	private Long accountNumber;// back-end will generate 10-digit random account number
	
	private Long customerId;
	
	private String accountType;
	
	private String branchAddress;
}

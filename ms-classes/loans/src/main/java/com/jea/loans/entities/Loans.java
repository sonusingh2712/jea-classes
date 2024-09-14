package com.jea.loans.entities;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loans {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	@UuidGenerator
	private String loanNumber;
	private String mobileNumber;
	private String loanType;
	private int totalLoan;
	private int amountPaid;
	private int outstandingAmount;
}

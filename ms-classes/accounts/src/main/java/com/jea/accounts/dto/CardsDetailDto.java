package com.jea.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsDetailDto {
	
	private String mobileNumber;
	private String cardNumber;
	private String cardType;
	
	private int totalLimit;
	private int amountUsed;
	private int availableAmount;
}
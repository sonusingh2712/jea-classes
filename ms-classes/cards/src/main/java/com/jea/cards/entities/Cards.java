package com.jea.cards.entities;

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
public class Cards {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;
	
	private String mobileNumber;
	
	private String cardNumber;
	private String cardType;
	
	private int totalLimit;
	private int amountUsed;
	private int availableAmount;
	
	public Cards(String mobileNumber, String cardNumber, String cardType, int totalLimit, int amountUsed, int availableAmount) {
		this.mobileNumber = mobileNumber;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.totalLimit = totalLimit;
		this.amountUsed = amountUsed;
		this.availableAmount = availableAmount;
	}
	
	
}

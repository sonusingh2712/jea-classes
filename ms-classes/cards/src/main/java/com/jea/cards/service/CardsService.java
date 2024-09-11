package com.jea.cards.service;

import java.util.List;

import com.jea.cards.dto.CardsDetailDto;

public interface CardsService {
	
	CardsDetailDto getCardById(Long cardId);
	
	List<CardsDetailDto> getAllCards();
	
	CardsDetailDto saveNewCard(String mobileNumber);
	
	boolean updateCardInfo(String mobileNumber, CardsDetailDto cardsDetailDto);
	
	boolean deleteCard(String mobileNumber);

	int checkAvailableBalance(String mobileNumber, int amountUsed);
}

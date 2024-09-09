package com.jea.cards.service;

import java.util.List;

import com.jea.cards.dto.CardsDetailDto;

public interface CardsService {
	
	CardsDetailDto getCardById(Long cardId);
	List<CardsDetailDto> getAllCards();
	
	
}

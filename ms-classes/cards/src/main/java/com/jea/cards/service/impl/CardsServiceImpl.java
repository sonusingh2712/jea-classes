package com.jea.cards.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import com.jea.cards.dto.CardsDetailDto;
import com.jea.cards.entities.Cards;
import com.jea.cards.repository.CardsRepository;
import com.jea.cards.service.CardsService;

import jakarta.persistence.EntityNotFoundException;

public class CardsServiceImpl implements CardsService {
	
	@Autowired
	private CardsRepository cardsRepository;

	@Override
	public CardsDetailDto getCardById(Long cardId) {
		Cards savedCard = cardsRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found for id :: "+cardId));
		CardsDetailDto cardsDetailDto = new CardsDetailDto();
		BeanUtils.copyProperties(savedCard, cardsDetailDto);
		return cardsDetailDto;
	}

	@Override
	public List<CardsDetailDto> getAllCards() {
		List<Cards> listOfSavedCards = cardsRepository.findAll();
		List<CardsDetailDto> listOfCards = setDbRecordsToDetailDto(listOfSavedCards);
		return listOfCards;
	}

	private List<CardsDetailDto> setDbRecordsToDetailDto(List<Cards> listOfSavedCards) {
		//If list does not contain any value then return null.
		if (ObjectUtils.isEmpty(listOfSavedCards)) {
			return null;
		}
		
		List<CardsDetailDto> listOfCards = new ArrayList<>();
		for (Cards savedCard : listOfSavedCards) {
			CardsDetailDto cardsDetailDto = new CardsDetailDto();
			BeanUtils.copyProperties(savedCard, cardsDetailDto);
			listOfCards.add(cardsDetailDto);
		}
		return listOfCards;
	}

}

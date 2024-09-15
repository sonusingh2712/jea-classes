package com.jea.cards.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.jea.cards.dto.CardsDetailDto;
import com.jea.cards.entities.Cards;
import com.jea.cards.repository.CardsRepository;
import com.jea.cards.service.CardsService;
import com.jea.cards.util.CardsConstants;
import com.jea.cards.util.CreditCardNumberGenerator;

import jakarta.persistence.EntityNotFoundException;

@Service
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
	public CardsDetailDto getCardByMobileNumber(String mobileNumber) {
		Cards savedCard = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new EntityNotFoundException("No any card is linked with mobile number ::"+mobileNumber));
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

	@Override
	public CardsDetailDto saveNewCard(String mobileNumber) {
		Cards newCardInfo = generateNewCard(mobileNumber);
		newCardInfo = cardsRepository.save(newCardInfo);
		
		CardsDetailDto cardsDetailDto = new CardsDetailDto();
		BeanUtils.copyProperties(newCardInfo, cardsDetailDto);
		return cardsDetailDto;
	}
	
	private Cards generateNewCard(String mobileNumber) {
		String newCardNumber = CreditCardNumberGenerator.
				generate(CardsConstants.CREDIT_CARD_PREFIX, CardsConstants.CREDIT_CARD_NUMBER_LENGTH);
		
		Cards newCardInfo = new Cards();
		newCardInfo.setMobileNumber(mobileNumber);
		newCardInfo.setCardNumber(newCardNumber);
		newCardInfo.setCardType(CardsConstants.CREDIT_CARD_TYPE);
		newCardInfo.setTotalLimit(CardsConstants.CREDIT_CARD_TOTAL_LIMIT);
		newCardInfo.setAmountUsed(CardsConstants.CREDIT_CARD_AMOUNT_USED);
		newCardInfo.setAvailableAmount(CardsConstants.CREDIT_CARD_AVAILABLE_AMOUNT);
		
		return newCardInfo;
	}

	@Override
	public boolean updateCardInfo(String mobileNumber, CardsDetailDto cardsDetailDto) {
		Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new EntityNotFoundException("No any card is linked with mobile number ::"+mobileNumber));
		BeanUtils.copyProperties(cardsDetailDto, cards);
		cards = cardsRepository.save(cards);
		return true;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new EntityNotFoundException("No any card is linked with mobile number ::"+mobileNumber));
		cardsRepository.deleteById(cards.getCardId());
		
		/** We can use direct DSL method also.*/
//		cardsRepository.deleteByMobileNumber(mobileNumber);
		return true;
	}

	@Override
	public int checkAvailableBalance(String mobileNumber, int amountUsed) {
		Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new EntityNotFoundException("No any card is linked with mobile number ::"+mobileNumber));
		int availableBalance = cards.getAvailableAmount() - amountUsed;
		cards.setAvailableAmount(availableBalance);
		cardsRepository.save(cards);
		return availableBalance;
	}
}
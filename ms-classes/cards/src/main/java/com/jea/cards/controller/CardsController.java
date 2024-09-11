package com.jea.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jea.cards.dto.CardsDetailDto;
import com.jea.cards.service.CardsService;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsController {
	
	@Autowired
	private CardsService cardsService;
	
	@GetMapping("all-cards")
	public List<CardsDetailDto> getAllCards(){
		return cardsService.getAllCards();
	}
	
	@GetMapping("/{cardId}")
	public CardsDetailDto getCardsInfoById(Long cardId) {
		return cardsService.getCardById(cardId);
	}
	
	@PostMapping("/new-card")
	public CardsDetailDto getNewCard(@RequestParam String mobileNumber) {
		return cardsService.saveNewCard(mobileNumber);
	}
	
	@PutMapping
	public boolean updateCardsInfo(@RequestParam String mobileNumber, @RequestBody CardsDetailDto cardsDetailDto) {
		return cardsService.updateCardInfo(mobileNumber, cardsDetailDto);
	}
	
	@DeleteMapping
	public boolean deleteCardsByMobileNumber(@RequestParam String mobileNumber) {
		return cardsService.deleteCard(mobileNumber);
	}
	
	@GetMapping
	public int checkAvailableBalance(@RequestParam String mobileNumber, @RequestParam int amountUsed) {
		return cardsService.checkAvailableBalance(mobileNumber, amountUsed);
	}
}

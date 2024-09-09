package com.jea.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

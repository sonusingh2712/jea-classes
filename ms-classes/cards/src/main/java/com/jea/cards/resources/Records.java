package com.jea.cards.resources;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jea.cards.service.CardsService;

import jakarta.annotation.PostConstruct;


@Component
public class Records {
	
	@Autowired
	private CardsService cardsService;
	
//	@PostConstruct
	public void saveListOfRecords() {
		List<String> listOfMobileNumber = Stream.of("9999988888","8888877777","7777766666","6666699999",
													"9999898888","8888787777","7777676666","6666969999").
										  collect(Collectors.toList());
		
		listOfMobileNumber.forEach(mobileNumber -> cardsService.saveNewCard(mobileNumber));
	}
	
}
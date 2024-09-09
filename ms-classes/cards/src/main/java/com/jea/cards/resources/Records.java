package com.jea.cards.resources;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jea.cards.entities.Cards;
import com.jea.cards.repository.CardsRepository;

//import jakarta.annotation.PostConstruct;

@Component
public class Records {
	
	@Autowired
	private CardsRepository cardsRepository;
	
//	@PostConstruct
	public void generateCardsRecord() {
		cardsRepository.saveAll(listOfCardsRecords());
	}
	
	public List<Cards> listOfCardsRecords(){
		return Stream.of(
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0),
				new Cards(null, null, null, 0, 0, 0)).
				collect(Collectors.toList());
	}
}
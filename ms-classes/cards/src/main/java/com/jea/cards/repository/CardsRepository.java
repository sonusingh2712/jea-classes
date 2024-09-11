package com.jea.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.cards.entities.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long>{

	//DSL Method, Find Cards Info Using Mobile Number
	Optional<Cards> findByMobileNumber(String mobileNumber);
	
}

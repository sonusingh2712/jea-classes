package com.jea.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jea.cards.entities.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long>{
	
}

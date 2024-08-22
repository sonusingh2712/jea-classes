package com.jea.entities;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@Column(unique = true)
	private String productName;
	private Double price;
	private Integer quantity;
	private String description;
	private Boolean isStock;
	@UuidGenerator
	private String barCode; // Bar-code should be auto-generated.
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
}
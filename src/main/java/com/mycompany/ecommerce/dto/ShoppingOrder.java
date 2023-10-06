package com.mycompany.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@Component
public class ShoppingOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String payment_id;
	LocalDateTime dateTime;
	double price;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<CustomerProduct> customerProducts;

}

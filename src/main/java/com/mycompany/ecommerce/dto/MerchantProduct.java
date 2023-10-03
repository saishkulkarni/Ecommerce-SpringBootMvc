package com.mycompany.ecommerce.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Component
@Entity
@Data
public class MerchantProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "*This is Required Field")
	private String name;
	@NotNull(message = "*This is Required Field")
	@DecimalMin(value = "1", message = "*Enter Value Greater than 1")
	private int stock;
	@NotNull(message = "*This is Required Field")
	@DecimalMin(value = "1", message = "*Enter Value Greater than 1")
	private double price;
	@NotEmpty(message = "*This is Required Field")
	private String category;
	boolean approved;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	byte[] picture;
}

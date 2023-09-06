package com.mycompany.ecommerce.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Merchant {
	@Id
	@GeneratedValue(generator = "merchant_id")
	@SequenceGenerator(name = "merchant_id", initialValue = 111001, allocationSize = 1, sequenceName = "merchant_id")
	private int id;
	@Size(min = 5, message = "Atleast Enter 5 Charecter")
	private String name;
	@Email(message = "Email Format is Not Correct")
	private String email;
	@Size(max = 10, message = "Number Should be 10 Digits")
	private long mobile;
	private String password;
	private String gender;
	private LocalDate dob;
}

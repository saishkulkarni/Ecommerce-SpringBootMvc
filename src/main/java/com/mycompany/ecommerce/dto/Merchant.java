package com.mycompany.ecommerce.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Component
public class Merchant {
	@Id
	@GeneratedValue(generator = "merchant_id")
	@SequenceGenerator(name = "merchant_id", initialValue = 111001, allocationSize = 1, sequenceName = "merchant_id")
	private int id;
	@Size(min = 5, message = "Atleast Enter 5 Charecter")
	private String name;
	@Email
	private String email;
	private long mobile;
	private String password;
	private String gender;
	@Past(message = "Date Must Not be Todays or Futures Date")
	private LocalDate dob;
}

package com.mycompany.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	@Size(min = 5, message = "*Atleast Enter 5 Charecter")
	private String name;
	@Email(message = "*Enter Proper Format")
	@NotEmpty(message = "*Must not be empty")
	private String email;
	@NotNull
	@DecimalMin(value = "6000000000", message = "*Enter Proper Number")
	@DecimalMax(value = "9999999999", message = "*Enter Proper Number")
	private long mobile;
	@Size(min = 8, message = "*Minimum 8 Charecters")
	private String password;
	@NotEmpty(message = "*Select atleast one gender")
	private String gender;
	@Past(message = "*Date Must Not be Todays or Futures Date")
	@NotNull(message = "*Must not be empty")
	private LocalDate dob;
	private boolean status;
	private int otp;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	List<MerchantProduct> products;
}

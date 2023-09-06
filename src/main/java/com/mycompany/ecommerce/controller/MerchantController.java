package com.mycompany.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	@GetMapping
	public String loadHome() {
		return "Merchant";
	}

	@GetMapping("/signup")
	public String loadSignup() {
		return "MerchantSignup";
	}
}

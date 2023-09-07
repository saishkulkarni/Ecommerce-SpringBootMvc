package com.mycompany.ecommerce.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.ecommerce.dto.Merchant;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

	@Autowired
	Merchant merchant;

	@GetMapping
	public String loadHome() {
		return "Merchant";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("merchant", merchant);
		return "MerchantSignup";
	}

	@PostMapping("/signup")
	public String signup(@Valid Merchant merchant,BindingResult result) {
		if(result.hasErrors())
			return "MerchantSignup";
		else
		return merchant.toString();
	}
}

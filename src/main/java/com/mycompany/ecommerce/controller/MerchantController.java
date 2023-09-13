package com.mycompany.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.ecommerce.dto.Merchant;
import com.mycompany.ecommerce.helper.LoginHelper;
import com.mycompany.ecommerce.service.MerchantService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

	@Autowired
	MerchantService merchantService;

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
	public String signup(@Valid Merchant merchant, BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			return "MerchantSignup";
		} else
			return merchantService.signup(merchant, modelMap);
	}

	@PostMapping("/verify-otp")
	public String verify(@RequestParam int otp, @RequestParam int id, ModelMap modelMap) {
		return merchantService.verfiyOtp(id, otp, modelMap);
	}
	
	@PostMapping("/login")
	public String login(LoginHelper helper,ModelMap map)
	{
		return merchantService.login(helper,map);
	}

}

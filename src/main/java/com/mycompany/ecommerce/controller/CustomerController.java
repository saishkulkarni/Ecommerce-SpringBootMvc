package com.mycompany.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.ecommerce.dto.Customer;
import com.mycompany.ecommerce.helper.LoginHelper;
import com.mycompany.ecommerce.service.CustomerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@Autowired
	Customer customer;

	@GetMapping
	public String loadHome() {
		return "Customer";
	}

	@GetMapping("/signup")
	public String loadSignup(ModelMap map) {
		map.put("customer", customer);
		return "CustomerSignup";
	}

	@PostMapping("/signup")
	public String signup(@Valid Customer customer, BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			return "CustomerSignup";
		} else
			return customerService.signup(customer, modelMap);
	}

	@GetMapping("/home")
	public String loadHome(HttpSession session, ModelMap modelMap) {
		if (session.getAttribute("customer") != null) {
			return "CustomerHome";
		} else {
			modelMap.put("neg", "Inavlid Session");
			return "Customer";
		}

	}

	@PostMapping("/verify-otp")
	public String verify(@RequestParam int otp, @RequestParam int id, ModelMap modelMap) {
		return customerService.verfiyOtp(id, otp, modelMap);
	}

	@PostMapping("/login")
	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		return customerService.login(helper, map, session);
	}
}

package com.mycompany.ecommerce.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.ecommerce.dto.Merchant;
import com.mycompany.ecommerce.dto.MerchantProduct;
import com.mycompany.ecommerce.helper.LoginHelper;
import com.mycompany.ecommerce.service.MerchantService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

	@Autowired
	MerchantService merchantService;

	@Autowired
	Merchant merchant;

	@Autowired
	MerchantProduct product;

	@GetMapping
	public String loadHome() {
		return "Merchant";
	}

	@GetMapping("/home")
	public String loadHome(HttpSession session, ModelMap modelMap) {
		if (session.getAttribute("merchant") != null) {
			return "MerchantHome";
		} else {
			modelMap.put("neg", "Inavlid Session");
			return "Merchant";
		}

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
	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		return merchantService.login(helper, map, session);
	}

	@GetMapping("/add-product")
	public String addProduct(ModelMap map, HttpSession session) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			map.put("product", product);
			return "AddProduct";
		} else {
			map.put("neg", "Invalid Session");
			return "Main";
		}
	}

	@PostMapping("/add-product")
	public String addProduct(@Valid MerchantProduct product, BindingResult result, @RequestParam MultipartFile pic,
			ModelMap map, HttpSession session) throws IOException {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			if (result.hasErrors())
				return "AddProduct";
			else {
				return merchantService.addProduct(product, pic, map, merchant, session);
			}
		} else {
			map.put("neg", "Invalid Session");
			return "Main";
		}
	}

	@GetMapping("/fetch-products")
	public String fetchProducts(HttpSession session, ModelMap modelMap) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			return merchantService.fetchProducts(merchant, modelMap);
		} else {
			modelMap.put("neg", "Invalid Session");
			return "Main";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id, HttpSession session, ModelMap modelMap) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			return merchantService.delete(id, modelMap, merchant, session);
		} else {
			modelMap.put("neg", "Invalid Session");
			return "Main";
		}
	}

	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable int id, HttpSession session, ModelMap modelMap) {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			return merchantService.edit(id, modelMap);
		} else {
			modelMap.put("neg", "Invalid Session");
			return "Main";
		}
	}

	@PostMapping("/update-product")
	public String updateProduct(@Valid MerchantProduct product, BindingResult result, @RequestParam MultipartFile pic,
			ModelMap map, HttpSession session) throws IOException {
		Merchant merchant = (Merchant) session.getAttribute("merchant");
		if (merchant != null) {
			if (result.hasErrors())
				return "EditProduct";
			else {
				return merchantService.editProduct(product, pic, map, merchant, session);
			}
		} else {
			map.put("neg", "Invalid Session");
			return "Main";
		}
	}
}

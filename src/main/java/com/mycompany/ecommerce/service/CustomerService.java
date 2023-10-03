package com.mycompany.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.dao.CustomerDao;
import com.mycompany.ecommerce.dao.ProductDao;
import com.mycompany.ecommerce.dto.Customer;
import com.mycompany.ecommerce.dto.CustomerProduct;
import com.mycompany.ecommerce.dto.MerchantProduct;
import com.mycompany.ecommerce.dto.ShoppingCart;
import com.mycompany.ecommerce.helper.AES;
import com.mycompany.ecommerce.helper.LoginHelper;
import com.mycompany.ecommerce.helper.MailHelper;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	MailHelper mailHelper;

	@Autowired
	ProductDao productDao;

	public String signup(Customer customer, ModelMap modelMap) {
		Customer customer1 = customerDao.fetchByEmail(customer.getEmail());
		Customer customer2 = customerDao.fetchByMobile(customer.getMobile());
		if (customer1 == null && customer2 == null) {
			int otp = new Random().nextInt(100000, 999999);
			customer.setPassword(AES.encrypt(customer.getPassword(), "123"));
			customer.setOtp(otp);
			customerDao.save(customer);
			// mailHelper.sendOtp(customer);
			modelMap.put("id", customer.getId());
			return "VerifyOtp2";
		} else {
			if (customer1 != null) {
				if (customer1.isStatus()) {
					modelMap.put("neg", "Email Already Exists");
					return "CustomerSignup";
				} else {
					if (customer2 != null) {
						mailHelper.sendOtp(customer1);
						modelMap.put("id", customer1.getId());
						return "VerifyOtp2";
					} else {
						modelMap.put("neg", "Same Email with Different Number Exists");
						return "CustomerSignup";
					}
				}
			} else {
				modelMap.put("neg", "Phone Number Already Exists");
				return "CustomerSignup";
			}
		}
	}

	public String verfiyOtp(int id, int otp, ModelMap modelMap) {
		Customer customer = customerDao.fetchById(id);
		if (customer == null) {
			modelMap.put("neg", "Something went Wrong");
			return "Main";
		} else {
			if (customer.getOtp() == otp) {
				customer.setStatus(true);
				customerDao.save(customer);
				modelMap.put("pos", "Account Verified Successfully");
				return "Customer";
			} else {
				modelMap.put("neg", "OTP MissMatch");
				modelMap.put("id", id);
				return "VerifyOtp2";
			}
		}
	}

	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		Customer customer = customerDao.fetchByEmail(helper.getEmail());
		if (customer == null) {
			map.put("neg", "Incorrect Email");
			return "Customer";
		} else {
			if (AES.decrypt(customer.getPassword(), "123").equals(helper.getPassword())) {
				if (customer.isStatus()) {
					session.setMaxInactiveInterval(150);
					session.setAttribute("customer", customer);
					map.put("pos", "Login Success");
					return "CustomerHome";
				} else {
					map.put("neg", "Verify Your OTP First");
//					mailHelper.sendOtp(customer);
					map.put("id", customer.getId());
					return "VerifyOtp2";
				}
			} else {
				map.put("neg", "Incorrect Password");
				return "Customer";
			}
		}
	}

	public String fetchProducts(ModelMap modelMap) {
		List<MerchantProduct> list = productDao.fetchAprovedProducts();
		if (list.isEmpty()) {
			modelMap.put("neg", "No Products Available");
			return "CustomerHome";
		} else {
			modelMap.put("list", list);
			return "CustomerProducts";
		}
	}

	public String addToCart(int id, HttpSession session, Customer customer, ModelMap modelMap) {
		MerchantProduct product = productDao.findById(id);
		if (product != null) {
			if (product.getStock() > 0) {
				ShoppingCart cart = customer.getCart();
				if (cart == null) {
					cart = new ShoppingCart();
					customer.setCart(cart);
				}
				List<CustomerProduct> customerProducts = cart.getCustomerProducts();
				if (customerProducts == null) {
					customerProducts = new ArrayList<CustomerProduct>();
				}

				boolean flag = true;
				for (CustomerProduct customerProduct : customerProducts) {
					if (customerProduct.getName().equals(product.getName())) {
						customerProduct.setQuantity(customerProduct.getQuantity() + 1);
						customerProduct.setPrice(customerProduct.getPrice() + product.getPrice());
						flag = false;
						break;
					}
				}
				if (flag) {
					CustomerProduct customerProduct = new CustomerProduct();
					customerProduct.setName(product.getName());
					customerProduct.setCategory(product.getCategory());
					customerProduct.setPicture(product.getPicture());
					customerProduct.setPrice(product.getPrice());
					customerProduct.setQuantity(1);
					customerProducts.add(customerProduct);
				}
				customerDao.save(customer);
				session.setAttribute("customer", customerDao.fetchById(customer.getId()));
				product.setStock(product.getStock() - 1);
				productDao.save(product);

				modelMap.put("pos", "Item Added to Cart");
				
			} else {
				modelMap.put("neg", "Out of Stock");
			}
			return fetchProducts(modelMap);
		} else {
			modelMap.put("neg", "Something went Wrong");
			return "Main";
		}
	}

}

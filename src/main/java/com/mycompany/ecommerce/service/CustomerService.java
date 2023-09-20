package com.mycompany.ecommerce.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.dao.CustomerDao;
import com.mycompany.ecommerce.dto.Customer;
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

	public String signup(Customer customer, ModelMap modelMap) {
		Customer customer1 = customerDao.fetchByEmail(customer.getEmail());
		Customer customer2 = customerDao.fetchByMobile(customer.getMobile());
		if (customer1 == null && customer2 == null) {
			int otp = new Random().nextInt(100000, 999999);
			customer.setPassword(AES.encrypt(customer.getPassword(), "123"));
			customer.setOtp(otp);
			customerDao.save(customer);
			mailHelper.sendOtp(customer);
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
					return "Customer";
				}
			} else {
				map.put("neg", "Incorrect Password");
				return "Customer";
			}
		}
	}

}

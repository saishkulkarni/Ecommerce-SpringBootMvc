package com.mycompany.ecommerce.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.dao.CustomerDao;
import com.mycompany.ecommerce.dto.Customer;
import com.mycompany.ecommerce.helper.MailHelper;

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

}

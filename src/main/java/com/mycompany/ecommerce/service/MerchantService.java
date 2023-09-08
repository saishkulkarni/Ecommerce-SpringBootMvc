package com.mycompany.ecommerce.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.ecommerce.dao.MerchantDao;
import com.mycompany.ecommerce.dto.Merchant;

@Service
public class MerchantService {

	@Autowired
	MerchantDao merchantDao;

	public String signup(Merchant merchant) {
		if(merchantDao.fetchByEmail(merchant.getEmail())==null && merchantDao.fetchByMobile(merchant.getMobile())==null)
		{
			int otp=new Random().nextInt(100000,999999);
			merchant.setOtp(otp);
			merchantDao.save(merchant);
			//Logic to Send Email
			
			return "VerifyOtp";
		}
		else {
			return "MerchantSignup";
		}
	}

}

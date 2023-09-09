package com.mycompany.ecommerce.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.dao.MerchantDao;
import com.mycompany.ecommerce.dto.Merchant;
import com.mycompany.ecommerce.helper.MailHelper;

@Service
public class MerchantService {

	@Autowired
	MerchantDao merchantDao;

	@Autowired
	MailHelper mailHelper;

	public String signup(Merchant merchant, ModelMap modelMap) {
		if (merchantDao.fetchByEmail(merchant.getEmail()) == null
				&& merchantDao.fetchByMobile(merchant.getMobile()) == null) {
			int otp = new Random().nextInt(100000, 999999);
			merchant.setOtp(otp);
			merchantDao.save(merchant);
			mailHelper.sendOtp(merchant);
			modelMap.put("id", merchant.getId());
			return "VerifyOtp";
		} else {
			modelMap.put("neg", "Email or Phone Already Exists");
			return "MerchantSignup";
		}
	}

}

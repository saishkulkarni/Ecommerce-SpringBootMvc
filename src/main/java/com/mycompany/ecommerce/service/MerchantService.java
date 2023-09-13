package com.mycompany.ecommerce.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.dao.MerchantDao;
import com.mycompany.ecommerce.dto.Merchant;
import com.mycompany.ecommerce.helper.LoginHelper;
import com.mycompany.ecommerce.helper.MailHelper;

@Service
public class MerchantService {

	@Autowired
	MerchantDao merchantDao;

	@Autowired
	MailHelper mailHelper;

	public String signup(Merchant merchant, ModelMap modelMap) {
		Merchant merchant1 = merchantDao.fetchByEmail(merchant.getEmail());
		Merchant merchant2 = merchantDao.fetchByMobile(merchant.getMobile());
		if (merchant1 == null && merchant2 == null) {
			int otp = new Random().nextInt(100000, 999999);
			merchant.setOtp(otp);
			merchantDao.save(merchant);
			mailHelper.sendOtp(merchant);
			modelMap.put("id", merchant.getId());
			return "VerifyOtp1";
		} else {
			if (merchant1 != null) {
				if (merchant1.isStatus()) {
					modelMap.put("neg", "Email Already Exists");
					return "MerchantSignup";
				} else {
					if (merchant2 != null) {
						mailHelper.sendOtp(merchant1);
						modelMap.put("id", merchant1.getId());
						return "VerifyOtp1";
					} else {
						modelMap.put("neg", "Same Email with Different Number Exists");
						return "MerchantSignup";
					}
				}
			} else {
				modelMap.put("neg", "Phone Number Already Exists");
				return "MerchantSignup";
			}
		}
	}

	public String verfiyOtp(int id, int otp, ModelMap modelMap) {
		Merchant merchant = merchantDao.fetchById(id);
		if (merchant == null) {
			modelMap.put("neg", "Something went Wrong");
			return "Main";
		} else {
			if (merchant.getOtp() == otp) {
				merchant.setStatus(true);
				merchantDao.save(merchant);
				modelMap.put("pos", "Account Verified Successfully");
				return "Merchant";
			} else {
				modelMap.put("neg", "OTP MissMatch");
				modelMap.put("id", id);
				return "VerifyOtp1";
			}
		}
	}

	public String login(LoginHelper helper, ModelMap map) {
		Merchant merchant=merchantDao.fetchByEmail(helper.getEmail());
		if(merchant==null)
		{
			map.put("neg", "InCorrect Email");
			return "Merchant";
		}
		else {
			if(merchant.getPassword().equals(helper.getPassword()))
			{
				if(merchant.isStatus())
				{
				map.put("pos", "Login Success");
				return "MerchantHome";
				}
				else {
					map.put("neg", "Verify Your OTP First");
					return "Merchant";
				}
			}
			else {
				map.put("neg", "InCorrect Password");
				return "Merchant";
			}
		}
	}

}

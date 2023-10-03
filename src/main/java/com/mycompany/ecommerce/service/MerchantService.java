package com.mycompany.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.ecommerce.dao.MerchantDao;
import com.mycompany.ecommerce.dao.ProductDao;
import com.mycompany.ecommerce.dto.Merchant;
import com.mycompany.ecommerce.dto.MerchantProduct;
import com.mycompany.ecommerce.helper.AES;
import com.mycompany.ecommerce.helper.LoginHelper;
import com.mycompany.ecommerce.helper.MailHelper;

import jakarta.servlet.http.HttpSession;

@Service
public class MerchantService {

	@Autowired
	MerchantDao merchantDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	MailHelper mailHelper;

	public String signup(Merchant merchant, ModelMap modelMap) {
		Merchant merchant1 = merchantDao.fetchByEmail(merchant.getEmail());
		Merchant merchant2 = merchantDao.fetchByMobile(merchant.getMobile());
		if (merchant1 == null && merchant2 == null) {
			int otp = new Random().nextInt(100000, 999999);
			merchant.setOtp(otp);
			merchant.setPassword(AES.encrypt(merchant.getPassword(), "123"));
			merchantDao.save(merchant);
			// mailHelper.sendOtp(merchant);
			modelMap.put("id", merchant.getId());
			return "VerifyOtp1";
		} else {
			if (merchant1 != null) {
				if (merchant1.isStatus()) {
					modelMap.put("neg", "Email Already Exists");
					return "MerchantSignup";
				} else {
					if (merchant2 != null) {
						// mailHelper.sendOtp(merchant1);
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

	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		Merchant merchant = merchantDao.fetchByEmail(helper.getEmail());
		if (merchant == null) {
			map.put("neg", "Incorrect Email");
			return "Merchant";
		} else {
			if (AES.decrypt(merchant.getPassword(), "123").equals(helper.getPassword())) {
				if (merchant.isStatus()) {
					session.setMaxInactiveInterval(150);
					session.setAttribute("merchant", merchant);
					map.put("pos", "Login Success");
					return "MerchantHome";
				} else {
					map.put("neg", "Verify Your OTP First");
					return "Merchant";
				}
			} else {
				map.put("neg", "Incorrect Password");
				return "Merchant";
			}
		}
	}

	public String addProduct(MerchantProduct product, MultipartFile pic, ModelMap map, Merchant merchant, HttpSession session)
			throws IOException {
		byte[] picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

		product.setPicture(picture);
		List<MerchantProduct> list = merchant.getProducts();

		if (list == null)
			list = new ArrayList<MerchantProduct>();

		list.add(product);
		merchant.setProducts(list);
		session.setAttribute("merchant", merchantDao.save(merchant));
		map.put("pos", "Product Added Success");
		return "MerchantHome";
	}

	public String fetchProducts(Merchant merchant, ModelMap modelMap) {
		List<MerchantProduct> list = merchant.getProducts();
		if (list.isEmpty()) {
			modelMap.put("neg", "No Products Available");
			return "MerchantHome";
		} else {
			modelMap.put("list", list);
			return "MerchantProducts";
		}
	}

	public String delete(int id, ModelMap modelMap, Merchant merchant, HttpSession session) {
		MerchantProduct product = productDao.findById(id);
		if (product == null) {
			modelMap.put("neg", "Something Went Wrong");
			return "Main";
		} else {

			for (MerchantProduct product1 : merchant.getProducts()) {
				if (product1.getName().equals(product.getName())) {
					product = product1;
					break;
				}
			}
			merchant.getProducts().remove(product);
			Merchant merchant2 = merchantDao.save(merchant);
			session.setAttribute("merchant", merchant2);
			productDao.delete(product);
			modelMap.put("pos", "Product Deleted Success");
			return fetchProducts(merchant2, modelMap);
		}
	}

	public String edit(int id, ModelMap modelMap) {
		MerchantProduct product = productDao.findById(id);
		if (product == null) {
			modelMap.put("neg", "Something Went Wrong");
			return "Main";
		} else {
			modelMap.put("product", product);
			return "EditProduct";
		}
	}

	public String editProduct(MerchantProduct product, MultipartFile pic, ModelMap map, Merchant merchant, HttpSession session)
			throws IOException {
		byte[] picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);

		if (picture.length == 0) {
			product.setPicture(productDao.findById(product.getId()).getPicture());
		} else {
			product.setPicture(picture);
		}
		productDao.save(product);
		Merchant merchant2 = merchantDao.fetchById(merchant.getId());
		session.setAttribute("merchant", merchant2);
		map.put("pos", "Product Updated Success");
		return fetchProducts(merchant2, map);
	}

}

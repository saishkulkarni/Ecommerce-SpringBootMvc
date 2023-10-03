package com.mycompany.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.dao.CustomerDao;
import com.mycompany.ecommerce.dao.MerchantDao;
import com.mycompany.ecommerce.dao.ProductDao;
import com.mycompany.ecommerce.dto.Customer;
import com.mycompany.ecommerce.dto.Merchant;
import com.mycompany.ecommerce.dto.MerchantProduct;
import com.mycompany.ecommerce.helper.LoginHelper;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	@Autowired
	ProductDao productDao;

	@Autowired
	MerchantDao merchantDao;

	@Autowired
	CustomerDao customerDao;

	public String login(LoginHelper helper, ModelMap map, HttpSession session) {
		if (helper.getEmail().equals("admin@jsp.com")) {
			if (helper.getPassword().equals("admin")) {
				session.setAttribute("admin", "admin");
				map.put("pos", "Login Success");
				return "AdminHome";
			} else {
				map.put("neg", "Incorrect Password");
				return "Admin";
			}
		} else {
			map.put("neg", "Incorrect Email");
			return "Admin";
		}
	}

	public String fetchProducts(ModelMap modelMap) {
		List<MerchantProduct> list = productDao.fetchAllProducts();
		if (list.isEmpty()) {
			modelMap.put("neg", "No Products Available");
			return "AdminHome";
		} else {
			modelMap.put("list", list);
			return "AdminProducts";
		}
	}

	public String changeProductStatus(int id, ModelMap map) {
		MerchantProduct product = productDao.findById(id);
		if (product == null) {
			map.put("neg", "Something Went Wrong");
			return "Main";
		} else {
			if (product.isApproved())
				product.setApproved(false);
			else
				product.setApproved(true);

			productDao.save(product);
			map.put("pos", "Status Updated Success");
			return fetchProducts(map);
		}
	}

	public String fetchMerchants(ModelMap modelMap) {
		List<Merchant> list = merchantDao.fetchAllMerchants();
		if (list.isEmpty()) {
			modelMap.put("neg", "No Merchant Enrolled");
			return "AdminHome";
		} else {
			modelMap.put("list", list);
			return "AdminMerchants";
		}
	}

	public String fetchCustomers(ModelMap modelMap) {
		List<Customer> list = customerDao.fetchAllCustomers();
		if (list.isEmpty()) {
			modelMap.put("neg", "No Customer Enrolled");
			return "AdminHome";
		} else {
			modelMap.put("list", list);
			return "AdminCustomers";
		}
	}

}

package com.mycompany.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.dao.ProductDao;
import com.mycompany.ecommerce.dto.Product;
import com.mycompany.ecommerce.helper.LoginHelper;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

	@Autowired
	ProductDao productDao;

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
		List<Product> list = productDao.fetchAllProducts();
		if (list.isEmpty()) {
			modelMap.put("neg", "No Products Available");
			return "AdminHome";
		} else {
			modelMap.put("list", list);
			return "AdminProducts";
		}
	}

	public String changeProductStatus(int id, ModelMap map) {
		Product product = productDao.findById(id);
		if (product == null) {
			map.put("neg", "Something Went Wrong");
			return "Main";
		} else {
			if(product.isApproved())
				product.setApproved(false);
			else
				product.setApproved(true);
			
			productDao.save(product); 
			map.put("pos", "Status Updated Success");
			return fetchProducts(map);
		}
	}

}

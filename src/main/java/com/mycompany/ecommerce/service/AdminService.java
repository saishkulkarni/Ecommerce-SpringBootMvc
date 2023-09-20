package com.mycompany.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mycompany.ecommerce.helper.LoginHelper;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService {

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

}

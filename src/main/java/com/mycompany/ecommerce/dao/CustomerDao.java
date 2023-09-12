package com.mycompany.ecommerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.ecommerce.dto.Customer;
import com.mycompany.ecommerce.repository.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	CustomerRepository customerRepository;

	public Customer fetchByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	public Customer fetchByMobile(long mobile) {
		return customerRepository.findByMobile(mobile);
	}

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer fetchById(int id)
	{
		return customerRepository.findById(id).orElse(null);
	}

}

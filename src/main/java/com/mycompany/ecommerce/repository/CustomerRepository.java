package com.mycompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.dto.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{

	Customer findByEmail(String email);

	Customer findByMobile(long mobile);

}

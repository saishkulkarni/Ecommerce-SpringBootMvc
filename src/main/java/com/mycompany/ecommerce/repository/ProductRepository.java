package com.mycompany.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.dto.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>
{

	List<Product> findByApprovedTrue();

}

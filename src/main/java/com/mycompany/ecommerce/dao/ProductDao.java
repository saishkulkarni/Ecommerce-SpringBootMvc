package com.mycompany.ecommerce.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.ecommerce.dto.Product;
import com.mycompany.ecommerce.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	ProductRepository productRepository;

	public Product findById(int id) {
		return productRepository.findById(id).orElse(null);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

}

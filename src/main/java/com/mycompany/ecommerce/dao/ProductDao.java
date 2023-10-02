package com.mycompany.ecommerce.dao;

import java.util.List;

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

	public void save(Product product) {
		productRepository.save(product);
	}

	public List<Product> fetchAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> fetchAprovedProducts() {
		return productRepository.findByApprovedTrue();
	}

}

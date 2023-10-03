package com.mycompany.ecommerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.ecommerce.dto.MerchantProduct;
import com.mycompany.ecommerce.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	ProductRepository productRepository;

	public MerchantProduct findById(int id) {
		return productRepository.findById(id).orElse(null);
	}

	public void delete(MerchantProduct product) {
		productRepository.delete(product);
	}

	public void save(MerchantProduct product) {
		productRepository.save(product);
	}

	public List<MerchantProduct> fetchAllProducts() {
		return productRepository.findAll();
	}

	public List<MerchantProduct> fetchAprovedProducts() {
		return productRepository.findByApprovedTrue();
	}

}

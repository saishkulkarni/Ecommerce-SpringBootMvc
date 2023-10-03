package com.mycompany.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.dto.MerchantProduct;

public interface ProductRepository extends JpaRepository<MerchantProduct, Integer> {
	List<MerchantProduct> findByApprovedTrue();
}

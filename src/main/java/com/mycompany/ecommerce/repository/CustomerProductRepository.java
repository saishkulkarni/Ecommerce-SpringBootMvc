package com.mycompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.dto.CustomerProduct;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Integer>
{

}

package com.mycompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.dto.ShoppingCart;

public interface CartRepository extends JpaRepository<ShoppingCart, Integer> {

}

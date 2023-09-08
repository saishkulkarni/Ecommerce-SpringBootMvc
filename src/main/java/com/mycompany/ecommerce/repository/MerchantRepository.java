package com.mycompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.dto.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Integer>
{

	Merchant findByEmail(String email);

	Merchant findByMobile(long mobile);

}

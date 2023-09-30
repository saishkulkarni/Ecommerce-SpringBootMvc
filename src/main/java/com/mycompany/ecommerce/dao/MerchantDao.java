package com.mycompany.ecommerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.ecommerce.dto.Merchant;
import com.mycompany.ecommerce.repository.MerchantRepository;

@Repository
public class MerchantDao {

	@Autowired
	MerchantRepository merchantRepository;

	public Merchant fetchByEmail(String email) {
		return merchantRepository.findByEmail(email);
	}

	public Merchant fetchByMobile(long mobile) {
		return merchantRepository.findByMobile(mobile);
	}

	public Merchant save(Merchant merchant) {
		return merchantRepository.save(merchant);
	}
	
	public Merchant fetchById(int id)
	{
		return merchantRepository.findById(id).orElse(null);
	}

	public List<Merchant> fetchAllMerchants() {
		return merchantRepository.findAll();
	}

}

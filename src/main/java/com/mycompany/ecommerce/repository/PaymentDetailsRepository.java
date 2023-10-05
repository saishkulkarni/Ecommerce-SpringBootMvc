package com.mycompany.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.ecommerce.dto.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer>
{

}

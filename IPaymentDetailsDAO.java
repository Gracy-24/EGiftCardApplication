package com.egiftcard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egiftcard.entity.PaymentDetails;

@Repository
public interface IPaymentDetailsDAO extends JpaRepository<PaymentDetails, Integer> {
	

}

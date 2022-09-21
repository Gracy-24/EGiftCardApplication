package com.egiftcard.service;

import java.util.List;

import com.egiftcard.entity.PaymentDetails;
import com.egiftcard.exception.DuplicatePaymentIdException;
import com.egiftcard.exception.NoSuchPaymentIdException;

public interface IPaymentService {
	
	List<PaymentDetails> getAllPaymentDetails();
	
	PaymentDetails getPaymentDetailsById(int paymentId)throws NoSuchPaymentIdException;
	
	PaymentDetails registerPaymentDetails(PaymentDetails paymentDetails) throws DuplicatePaymentIdException;
	
	PaymentDetails updatePaymentDetailsById(int paymentId, PaymentDetails paymentDetails) throws NoSuchPaymentIdException;

	String deletePaymentDetailsById(int paymentId)throws NoSuchPaymentIdException;





	

	

}

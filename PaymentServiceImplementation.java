package com.egiftcard.service;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egiftcard.dao.IPaymentDetailsDAO;
import com.egiftcard.entity.PaymentDetails;
import com.egiftcard.exception.NoSuchPaymentIdException;
import com.egiftcard.exception.DuplicatePaymentIdException;
import com.egiftcard.exception.NoPaymentDetailsException;



@Service
public class PaymentServiceImplementation implements IPaymentService {
	
	@Autowired
	private IPaymentDetailsDAO payRepo;
	
	@Override
	public List<PaymentDetails> getAllPaymentDetails(){
			return payRepo.findAll();}

	
	@Override
	public PaymentDetails getPaymentDetailsById(int paymentId) throws NoSuchPaymentIdException {
		
		Optional<PaymentDetails>pd=payRepo.findById(paymentId);
		if(!pd.isPresent()) {
			throw new NoSuchPaymentIdException("Sorry! Payment Id "+paymentId+" does not exists");	
		}
		return pd.get();}
	
	
	
	@Override
	public PaymentDetails registerPaymentDetails(PaymentDetails paymentDetails) throws DuplicatePaymentIdException {
		Optional<PaymentDetails> payDetails=payRepo.findById(paymentDetails.getPaymentId());
		if(payDetails.isPresent()) {
			throw new DuplicatePaymentIdException("Payment Details with Payment Id "+paymentDetails.getPaymentId()+" already exists");}
		return payRepo.save(paymentDetails);}
	
	
	
	

	@Override
	public PaymentDetails updatePaymentDetailsById(int paymentId, PaymentDetails paymentDetails) throws NoSuchPaymentIdException{
		
		Optional<PaymentDetails> paymentUpdate=payRepo.findById(paymentId);
		if(!paymentUpdate.isPresent()) {
			throw new NoSuchPaymentIdException("Payment Id "+paymentId+" does not exists");	
		}
		PaymentDetails paymentUpdateById= payRepo.findById(paymentId).get();
		paymentUpdateById.setCardExpiry(paymentDetails.getCardExpiry());
		paymentUpdateById.setCardNumber(paymentDetails.getCardNumber());
		paymentUpdateById.setCvv(paymentDetails.getCvv());
		paymentUpdateById.setNameOnCard(paymentDetails.getNameOnCard());
		paymentUpdateById.setPaymentAmount(paymentDetails.getPaymentAmount());
		paymentUpdateById.setPaymentDate(paymentDetails.getPaymentDate());
		return payRepo.save(paymentUpdateById);
		}
		

	

	@Override
	public String deletePaymentDetailsById(int paymentId) throws NoPaymentDetailsException   {
		Optional<PaymentDetails>pd=payRepo.findById(paymentId);
		if(pd.isPresent()) {
		payRepo.deleteById(paymentId);
		return "Deleted Successfully";
		}
		else 
			throw new NoPaymentDetailsException("No Payment Details Available! Please enter correct payment ID");
	}
	}


	
	
	
	
	
	
	
	



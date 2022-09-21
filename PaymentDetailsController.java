package com.egiftcard.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.egiftcard.entity.PaymentDetails;
import com.egiftcard.exception.DuplicatePaymentIdException;
import com.egiftcard.service.IPaymentService;


@RestController
@RequestMapping("/api/paymentDetails")
public class PaymentDetailsController {

	
	@Autowired
	private IPaymentService paymentService;

	
	
	// http://localhost:8080/EGiftCardApp/api/payments
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/payments")
	public ResponseEntity<List<PaymentDetails>> getAllPaymentDetails() {
		List<PaymentDetails> paymentDetails = paymentService.getAllPaymentDetails();
		if (paymentDetails.isEmpty()) {
			return new ResponseEntity("No Payments made!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(paymentDetails, HttpStatus.OK);
	}

	
	
	// http://localhost:8080/EGiftCardApp/api/123
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{payment_id}")
	public ResponseEntity<PaymentDetails> getPaymentDetailsById(@Valid @PathVariable("payment_id") int paymentId) {
		PaymentDetails paymentDetails = paymentService.getPaymentDetailsById(paymentId);
		if (paymentDetails == null) {
			return new ResponseEntity("Sorry! Payment Details by Id not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(paymentDetails, HttpStatus.OK);
	}
	
	
	
	// http://localhost:8080/EGiftCardApp/api/add
	@PostMapping("/add")
	public ResponseEntity<PaymentDetails> registerPaymentDetails(@Valid @RequestBody PaymentDetails paymentDetails)
			throws DuplicatePaymentIdException {

		return new ResponseEntity<>(paymentService.registerPaymentDetails(paymentDetails), HttpStatus.CREATED);
	}

	
	
	
	// http://localhost:8080/EGiftCardApp/api/123
	@PutMapping("/{payment_id}")
	public ResponseEntity<PaymentDetails> updatePaymentDetailsById(@PathVariable("payment_id") int paymentId,
			@Valid @RequestBody PaymentDetails paymentDetails) {
		return new ResponseEntity<>(paymentService.updatePaymentDetailsById(paymentId, paymentDetails), HttpStatus.OK);
	}

	
	
	// http://localhost:8080/EGiftCardApp/api/123
	@DeleteMapping("/{payment_id}")
	public ResponseEntity<String> deletePaymentDetailsById(@PathVariable("payment_id") int paymentId,
			@Valid @RequestBody PaymentDetails paymentDetails) {
		String payDelete = new String();
		payDelete = paymentService.deletePaymentDetailsById(paymentId);
		return new ResponseEntity<>(payDelete, HttpStatus.OK);
	}

}

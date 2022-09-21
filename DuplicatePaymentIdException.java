package com.egiftcard.exception;

@SuppressWarnings("serial")
public class DuplicatePaymentIdException extends RuntimeException{
	public DuplicatePaymentIdException(String msg) {
		super(msg);
	}

}

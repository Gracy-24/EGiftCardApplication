package com.egiftcard.exception;

@SuppressWarnings("serial")
public class NoPaymentDetailsException extends RuntimeException{
	public NoPaymentDetailsException(String msg) {
		super(msg);
	}

}

package com.egiftcard.exception;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoSuchPaymentIdException.class)
	public ResponseEntity<ExceptionResponse> handleDuplicateIDException(NoSuchPaymentIdException e){
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorCode("NOT_FOUND");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return responseEntyity;
	}
	
	
	@ExceptionHandler(DuplicatePaymentIdException.class)
	public ResponseEntity<ExceptionResponse> handleDuplicateIDException(DuplicatePaymentIdException e){
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorCode("CONFLICT");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<>(response,HttpStatus.CONFLICT);
		return responseEntyity;
	}
	
	
	@ExceptionHandler(NoPaymentDetailsException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidDataException(NoPaymentDetailsException e){
		ExceptionResponse response=new ExceptionResponse();
		response.setErrorCode("NOT_FOUND");
		response.setErrorMessage(e.getMessage());
		response.setTimestamp(LocalDateTime.now());
		ResponseEntity<ExceptionResponse> responseEntyity=new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return responseEntyity;
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        ExceptionResponse response=new ExceptionResponse();
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        String errorMessage=null;
        for(String key:errors.keySet()) {
            errorMessage=errors.get(key);
        }
        response.setErrorCode("UNSUPPORTED_MEDIA_TYPE");
        response.setErrorMessage(errorMessage);
        response.setTimestamp(LocalDateTime.now());
        ResponseEntity<ExceptionResponse> responseEntity=new ResponseEntity<>(response,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        return responseEntity;
    }
	
}



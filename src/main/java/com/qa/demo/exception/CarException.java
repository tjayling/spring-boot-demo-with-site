package com.qa.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason ="Car not found!")
public class CarException extends Exception {
	private static final long serialVersionUID = -4471362852600200036L;
	
	

}

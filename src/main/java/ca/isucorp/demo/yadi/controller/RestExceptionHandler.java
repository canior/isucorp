package ca.isucorp.demo.yadi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class RestExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	List<String> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
		List<String> errorMessages = new ArrayList<String>();
		errorMessages.add("Please input a valid argument");
		return errorMessages;
	}
	
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	List<String> methodArgumentNotValidExceptionHanlder(MethodArgumentNotValidException exception) {
		return exception.getBindingResult()
		        .getAllErrors().stream()
		        .map(ObjectError::getDefaultMessage)
		        .collect(Collectors.toList());
	}
	
	@ResponseBody
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	List<String> BindExceptionHandler(BindException exception) {
		return exception.getBindingResult()
		        .getAllErrors().stream()
		        .map(ObjectError::getDefaultMessage)
		        .collect(Collectors.toList());
	}
}
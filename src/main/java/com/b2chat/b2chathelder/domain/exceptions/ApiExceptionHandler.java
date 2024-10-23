package com.b2chat.b2chathelder.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public List<String> handleContraintViolationException(HttpServletRequest request, Exception exception) {
		final List<String> errors = new ArrayList<String>();

		ConstraintViolationException ex = (ConstraintViolationException) exception;

		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			// formatting error message for client
			errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
		}

		return errors;
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ UniqueConstraintException.class })
	@ResponseBody
	public String handleConflictUniqueConstraintException(HttpServletRequest request, Exception exception) {
		return exception.getMessage();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundException.class })
	@ResponseBody
	public String handleNotFoundException(HttpServletRequest request, Exception exception) {
		return exception.getMessage();
	}
}

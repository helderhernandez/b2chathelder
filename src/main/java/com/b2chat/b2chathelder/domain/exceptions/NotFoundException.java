package com.b2chat.b2chathelder.domain.exceptions;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String detail) {
		super(detail);
	}
}

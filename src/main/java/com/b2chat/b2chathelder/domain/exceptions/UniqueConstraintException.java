package com.b2chat.b2chathelder.domain.exceptions;

public class UniqueConstraintException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UniqueConstraintException(String detail) {
		super(detail);
	}
}

package com.b2chat.b2chathelder.domain.exceptions;

public class ConflictUniqueConstraintException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ConflictUniqueConstraintException(String detail) {
		super(detail);
	}
}

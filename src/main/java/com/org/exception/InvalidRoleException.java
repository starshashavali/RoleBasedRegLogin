package com.org.exception;

public class InvalidRoleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidRoleException(String msg) {
		super(msg);
	}

}

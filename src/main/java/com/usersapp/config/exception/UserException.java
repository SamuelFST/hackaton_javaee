package com.usersapp.config.exception;

import java.io.Serializable;

public class UserException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public UserException(String message) {
		super(message);
	}
}

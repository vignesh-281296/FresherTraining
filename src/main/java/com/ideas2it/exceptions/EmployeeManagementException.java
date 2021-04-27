package com.ideas2it.exceptions;

/**
 * custom exception handler class
 * @author vignesh r
 */
public class EmployeeManagementException extends Exception {
	
	
	public EmployeeManagementException(String message) {
		super(message);
	}

	public EmployeeManagementException(Throwable cause) {
		super(cause);
	}

	public EmployeeManagementException(String message, Throwable cause) {
		super(message, cause);
	}
}

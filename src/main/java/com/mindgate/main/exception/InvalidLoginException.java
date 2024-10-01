package com.mindgate.main.exception;

public class InvalidLoginException  extends RuntimeException{
	
	public InvalidLoginException() {
		// TODO Auto-generated constructor stub
		System.out.println("Invalid Username or Password");
	}

	@Override
	public String getMessage() {
		return "Invalid Username or Password";
	}

}

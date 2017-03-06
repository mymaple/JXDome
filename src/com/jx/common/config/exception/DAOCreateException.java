package com.jx.common.config.exception;

public class DAOCreateException extends XException
{
	public DAOCreateException(String message){
		super(message);
	}

	public DAOCreateException(Throwable ex){
		super(ex);
	}
}
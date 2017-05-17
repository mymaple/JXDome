package com.jx.common.config.exception;

public class DAOCreateException extends XException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOCreateException(String message){
		super(message);
	}

	public DAOCreateException(Throwable ex){
		super(ex);
	}
}
package com.jx.common.config.exception;

public class DataAccessException extends XException
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public DataAccessException(String message)
  {
    super(message);
  }

  public DataAccessException(Throwable ex) {
    super(ex);
  }
}
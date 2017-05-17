package com.jx.common.config.exception;

public class ObjectCreateException extends XException
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ObjectCreateException(String message)
  {
    super(message);
  }

  public ObjectCreateException(Throwable ex) {
    super(ex);
  }
}
package com.jx.common.config.exception;

public class XException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int messageType = 1;
	
	public XException() {
	}
  public XException(String message, Throwable ex) {
    super(message, ex);
  }
  public XException(Throwable e) {
    super(e);
  }

  public XException(String message) {
    super(message);
  }

  public int getExceptionType()
  {
    return this.messageType;
  }

  public boolean isApplicationException() {
    return getExceptionType() == 1;
  }

  public boolean isCustomerException() {
    return getExceptionType() == 0;
  }

  public boolean isSystemException() {
    return getExceptionType() == 2;
  }
}

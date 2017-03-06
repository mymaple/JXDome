package com.jx.common.config.exception;

public class DataAccessException extends XException
{
  public DataAccessException(String message)
  {
    super(message);
  }

  public DataAccessException(Throwable ex) {
    super(ex);
  }
}
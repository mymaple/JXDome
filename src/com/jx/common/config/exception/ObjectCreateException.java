package com.jx.common.config.exception;

public class ObjectCreateException extends XException
{
  public ObjectCreateException(String message)
  {
    super(message);
  }

  public ObjectCreateException(Throwable ex) {
    super(ex);
  }
}
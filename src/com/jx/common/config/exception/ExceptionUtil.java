package com.jx.common.config.exception;

public class ExceptionUtil
{
  public static void throwIllegalArgumentExceptionWhenNull(Object object, String argumentName)
  {
    if (object == null)
      throw new IllegalArgumentException("Parameter '" + argumentName + "' cannot be null!");
  }

  public static void throwIllegalArgumentExceptionWhenNullOrEmpty(String str, String argumentName)
  {
    if (str == null) {
      throw new IllegalArgumentException("Parameter '" + argumentName + "' cannot be null!");
    }
    if (str.length() == 0)
      throw new IllegalArgumentException("Parameter '" + argumentName + "' cannot be empty!");
  }

  public static void throwIllegalArgumentExceptionWhenNull(Object object)
  {
    if (object == null)
      throw new IllegalArgumentException("Parameter cannot be null!");
  }
}

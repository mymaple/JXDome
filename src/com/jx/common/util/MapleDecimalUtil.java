package com.jx.common.util;

import java.math.BigDecimal;

public class MapleDecimalUtil {
	
	// 加法 
	public static Double addDefealt(Object a,Object b) throws Exception{
	    BigDecimal add1 = conversionToBigDecimal(a);
	    BigDecimal add2 = conversionToBigDecimal(b);
	    Double result = add1.add(add2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	    return result;
	}
	 
	// 减法
	public static Double subtractDefealt(Object a,Object b) throws Exception{
	    BigDecimal subtract1 = conversionToBigDecimal(a);
	    BigDecimal subtract2 = conversionToBigDecimal(b);
	    Double result = subtract1.subtract(subtract2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	    return result;
	}

	// 乘法
	public static Double multiplyDefealt(Object a,Object b) throws Exception{
	    BigDecimal multiply1 = conversionToBigDecimal(a);
	    BigDecimal multiply2 = conversionToBigDecimal(b);
	    Double result = multiply1.multiply(multiply2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	    return result;
	}
	 
	// 除法
	public static Double divideDefealt(Object a,Object b) throws Exception{
	    BigDecimal divide1 = conversionToBigDecimal(a);
	    BigDecimal divide2 = conversionToBigDecimal(b);
	    Double result = divide1.divide(divide2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	    return result;
	}
	
	
	// 加法 
	public static BigDecimal add(Object a,Object b) throws Exception{
	    BigDecimal add1 = conversionToBigDecimal(a);
	    BigDecimal add2 = conversionToBigDecimal(b);
	    return add1.add(add2);
	}
	
	// 减法
	public static BigDecimal subtract(Object a,Object b) throws Exception{
	    BigDecimal subtract1 = conversionToBigDecimal(a);
	    BigDecimal subtract2 = conversionToBigDecimal(b);
	    return subtract1.subtract(subtract2);
	}

	// 乘法
	public static BigDecimal multiply(Object a,Object b) throws Exception{
	    BigDecimal multiply1 = conversionToBigDecimal(a);
	    BigDecimal multiply2 = conversionToBigDecimal(b);
	    return multiply1.multiply(multiply2);
	}
	 
	// 除法
	public static BigDecimal divide(Object a,Object b) throws Exception{
	    BigDecimal divide1 = conversionToBigDecimal(a);
	    BigDecimal divide2 = conversionToBigDecimal(b);
	    return divide1.divide(divide2);
	}
	
	// 默认精度
	public static double defaultScal(BigDecimal deci) throws Exception{
		return deci.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	// 类型转换
	public static BigDecimal conversionToBigDecimal(Object obj) throws Exception{
	  if (obj instanceof String)
	  {
	   return new BigDecimal((String)obj);
	  }
	  else if (obj instanceof Double)
	  {
	   return new BigDecimal(obj.toString());
	  }
	  else if (obj instanceof Float)
	  {
	   return new BigDecimal(obj.toString());
	  }
	  else if (obj instanceof Integer)
	  {
	   return new BigDecimal(obj.toString());
	  }
	  else if (obj instanceof Long)
	  {
	   return new BigDecimal(obj.toString());
	  }
	  else if (obj instanceof Byte)
	  {
	   return new BigDecimal(obj.toString());
	  }
	  else if (obj instanceof Short)
	  {
	   return new BigDecimal(obj.toString());
	  }else
	  {
	   throw new Exception("不能转换");
	  }
	}
	
}

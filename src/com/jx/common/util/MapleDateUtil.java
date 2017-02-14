package com.jx.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MapleDateUtil {
	
	public final static SimpleDateFormat SDF_YEAR = new SimpleDateFormat("yyyy");

	public final static SimpleDateFormat SDF_DAY = new SimpleDateFormat("yyyy-MM-dd");

	public final static SimpleDateFormat SDF_DAY1 = new SimpleDateFormat("yyyyMMdd");

	public final static SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public final static SimpleDateFormat SDF_TIME1 = new SimpleDateFormat("yyyyMMddHHmmss");

	public static enum SDF{  
	    YEAR,DAY,DAY1,TIME,TIME1
	} 
	
	/**
	 * 获取sdf格式 字符串 
	 * 默认yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return SDF_TIME.format(date);
	}
	
	/**
	 * 获取sdf格式 日期
	 * 默认yyyy-MM-dd HH:mm:ss
	 * @param dateStr
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDateStr(String dateStr) throws ParseException {
		return SDF_TIME.parse(dateStr);
	}
	
	
	/**
	 * 获取sdf格式 字符串
	 * @param sdf
	 * @param date
	 * @return
	 */
	public static String formatDate(SimpleDateFormat sdf, Date date) {
		return sdf.format(date);
	}	
	
	/**
	 * 获取sdf格式 字符串
	 * @param sdf
	 * @param date
	 * @return
	 */
	public static String formatDate(SDF sdf, Date date) {
		switch (sdf) {
		case YEAR:
			return SDF_YEAR.format(date);
		case DAY:
			return SDF_DAY.format(date);
		case DAY1:
			return SDF_DAY1.format(date);
		case TIME:
			return SDF_TIME.format(date);
		case TIME1:
			return SDF_TIME1.format(date);
		default:
			return date.toString();
		}
	}
	
	/**
	 * 获取当前时间sdf格式 字符串
	 * @param sdf
	 * @return
	 */
	public static String getNow(SimpleDateFormat sdf) {
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前时间sdf格式 字符串
	 * @param sdf
	 * @return
	 */
	public static String getNow(SDF sdf) {
		switch (sdf) {
		case YEAR:
			return SDF_YEAR.format(new Date());
		case DAY:
			return SDF_DAY.format(new Date());
		case DAY1:
			return SDF_DAY1.format(new Date());
		case TIME:
			return SDF_TIME.format(new Date());
		case TIME1:
			return SDF_TIME1.format(new Date());
		default:
			return new Date().toString();
		}
	}
	
	/**
	 * 获取sdf格式 日期
	 * @param sdf
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDateStr(SimpleDateFormat sdf, String dateStr) throws ParseException {
		return sdf.parse(dateStr);
	}
	
	/**
	 * 获取sdf格式 日期
	 * @param sdf
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseDateStr(SDF sdf, String dateStr) throws ParseException {
		switch (sdf) {
		case YEAR:
			return SDF_YEAR.parse(dateStr);
		case DAY:
			return SDF_DAY.parse(dateStr);
		case DAY1:
			return SDF_DAY1.parse(dateStr);
		case TIME:
			return SDF_TIME.parse(dateStr);
		case TIME1:
			return SDF_TIME1.parse(dateStr);
		default:
			return null;
		}
	}

	/**
	 * 比较sdf1格式下的dateStr1与sdf2格式下的dateStr2
	 * @param sdf1
	 * @param dateStr1
	 * @param sdf2
	 * @param dateStr2
	 * @return 2 异常; 1 dateStr1>dateStr2; 0 dateStr1=dateStr2;-1 dateStr1<dateStr2
	 */
	public static int compareDateStr(SDF sdf1, String dateStr1, SDF sdf2, String dateStr2) {
		try {
			return parseDateStr(sdf1, dateStr1).compareTo(parseDateStr(sdf2, dateStr2));
		} catch (ParseException e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 2;
		}
	}
	
	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(SDF sdf, String dateStr) {
		try {
			parseDateStr(sdf, dateStr);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * 相差天数(精准比较)
	 * @param date1
	 * @param date2
	 * @return
	 */
    public static int getDaySpace(Date date1, Date date2) {
        return (int)(date1.getTime()-date2.getTime())/(24 * 60 * 60 * 1000);
    }
    
    /**
	 * 相差月数 date1-date2(精准比较)
	 * @param date1
	 * @param date2
	 * @return
	 */
    public static int getMonthSpace(Date date1, Date date2) {
    	int result = 0;
    	Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);//20170211
        c2.setTime(date2);//20170121
        
        result = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH) ;
        result += (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 12;
        c1.set(Calendar.YEAR, 0);
    	c1.set(Calendar.MONTH, 0);
    	c2.set(Calendar.YEAR, 0);
    	c2.set(Calendar.MONTH, 0);
        if(result == 0){
        	result = 0;
        }else if(result > 0){
        	result += c1.getTime().getTime()-c2.getTime().getTime() >= 0 ? 0 : -1;
        }else if(result < 0){
        	result += c1.getTime().getTime()-c2.getTime().getTime() > 0 ? 1 : 0;
        }
        return result;
    }
    
    public static void main(String[] args) throws ParseException {
    	
//		System.out.println(getYearSpace(parseDateStr(SDF.TIME, "2016-01-13 15:00:00"),new Date()));
		System.out.println(parseDateStr(SDF_TIME, "2016-01-13 15:00:00"));
//		System.out.println(getDaySpace(parseDateStr(SDF.DAY1, "20170113"),new Date()));
	}
    
    /**
     * 相差年数(精准比较)
     * @param date1
     * @param date2
     * @return
     */
    public static int getYearSpace(Date date1, Date date2) {
    	int result = 0;
    	Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);//20170211
        c2.setTime(date2);//20170121
        
        result = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
        c1.set(Calendar.YEAR, 0);
    	c2.set(Calendar.YEAR, 0);
        if(result == 0){
        	result = 0;
        }else if(result > 0){
        	result += c1.getTime().getTime()-c2.getTime().getTime() >= 0 ? 0 : -1;
        }else if(result < 0){
        	result += c1.getTime().getTime()-c2.getTime().getTime() > 0 ? 1 : 0;
        }
        return result;
    }


	/**
	 * 闰年判断
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if (year % 400 == 0)
			return true;
		if (year % 4 == 0)
			return year % 100 != 0;
		return false;
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		switch (calendar.get(Calendar.MONTH)) {
		case 0:
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 1:
			calendar.set(Calendar.DAY_OF_MONTH, 28);
			break;
		case 2:
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 3:
			calendar.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 4:
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 5:
			calendar.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 6:
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 7:
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 8:
			calendar.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 9:
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			break;
		case 10:
			calendar.set(Calendar.DAY_OF_MONTH, 30);
			break;
		case 11:
			calendar.set(Calendar.DAY_OF_MONTH, 31);
		}

		if ((calendar.get(Calendar.MONTH) == 1) && 
				(isLeapYear(calendar.get(Calendar.MONTH)))) {
			calendar.set(5, 29);
		}
		return calendar.getTime();
	}
	
	public static Date getNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	public static Date getPreviousMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	public static Date getFirstDayOfPreviousMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTime(getPreviousMonth(calendar.getTime()));
		calendar.setTime(getFirstDayOfMonth(calendar.getTime()));
		return calendar.getTime();
	}

	public static Date getLastDayOfPreviousMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTime(getPreviousMonth(calendar.getTime()));
		calendar.setTime(getLastDayOfMonth(calendar.getTime()));
		return calendar.getTime();
	}

	public static Date getFirstDayOfNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTime(getNextMonth(calendar.getTime()));
		calendar.setTime(getFirstDayOfMonth(calendar.getTime()));
		return calendar.getTime();
	}

	public static Date getLastDayOfNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTime(getNextMonth(calendar.getTime()));
		calendar.setTime(getLastDayOfMonth(calendar.getTime()));
		return calendar.getTime();
	}

	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getPreviousDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	public static Date getNextDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	public static Date getPreviousDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		return calendar.getTime();
	}
	
	public static Date getNextSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add (Calendar.SECOND, 1);
		return calendar.getTime();
	}
	
}

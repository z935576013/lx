package com.sdx.lx.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类
 *
 * @author lifei
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DateUtils {

	public final static String DATEFORMATE_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	public final static String DATEFORMATE_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";

	public final static String DATEFORMATE_YYYYMMDDHHMMSS = "yyyyMMddhhmmss";

	public final static String DATEFORMATE_YYYYMMDD = "yyyyMMdd";
	
	public final static String DATEFORMATE_YYYYMM = "yyyyMM";

	public final static String DATEFORMATE_YYYY_MM_DD = "yyyy-MM-dd";
	
	public final static String DATEFORMATE_HHMM = "HH:mm";
	
	public final static java.util.Date getDateOfString(String dateString,
			String dateFormate) {
		return getDateOfString(dateString, dateFormate, false);
	}

	public final static java.util.Date getDateOfString(String dateString,
			String dateFormate, boolean ignoreException) {
		if (StringUtils.isEmpty(dateFormate)) {
			dateFormate = DATEFORMATE_YYYYMMDD_HHMMSS;
		}
		DateFormat dataFormat = new java.text.SimpleDateFormat(dateFormate);
		java.util.Date date = null;
		try {
			date = dataFormat.parse(dateString);
		} catch (Exception e) {
			if (!ignoreException) {
				throw new RuntimeException(e);
			}
		}
		return date;
	}

	public final static java.util.Date getDateOfString(String dateString) {
		return getDateOfString(dateString, DATEFORMATE_YYYYMMDD_HHMMSS);
	}

	public final static String formatDate(Date date, String format) {
		return DateFormatUtils.format(date, format);
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			smdate = sdf.parse(sdf.format(smdate));
			bdate = sdf.parse(sdf.format(bdate));
		} catch (ParseException e) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * 提前几小时后的日期
	 * 
	 * @param srcDate
	 *            原始日期
	 * @param hour
	 *            推迟的小时数
	 * @return
	 */
	public static Date preHour(Date srcDate, int hour) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(srcDate);
		cal.add(GregorianCalendar.HOUR, -hour);
		return cal.getTime();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Long getCurrentDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}
	/**
	 * 获取前一天 ，num = 1
	 * 获取后一天 ，num = -1
	 * @return
	 */
	public static Date getPreDate(Date date,int num){
		if(null != date){
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			int day = calendar.get(Calendar.DATE); 
			calendar.set(Calendar.DATE, day - num);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 
	 * 功能描述:一天的开始时间 <br>
	 * 〈功能详细描述〉
	 *
	 * @param date
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static Date getStartDate(Date date){
	    if(null != date){
	        Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
	    }
	    return null;
	}
	
	/**
	 * 
	 * 功能描述: 一天的结束时间<br>
	 * 〈功能详细描述〉
	 *
	 * @param date
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static Date getEndDate(Date date){
	    if(null != date){
	        Calendar calendar=Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.set(Calendar.HOUR_OF_DAY, 23);
	        calendar.set(Calendar.MINUTE, 59);
	        calendar.set(Calendar.SECOND, 59);
	        return calendar.getTime();
	    }
	    return null;
	}
	
}

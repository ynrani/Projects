package com.itap.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.itap.constant.ITAPConstants;
import com.itap.exception.ServiceException;

public class DateUtils {
	public static void main(String[] args) {
		long input = 1458282312187l;

		String[] jobs = { "AXA_TDEKJFJ_SHFJ_JJJJ", "AXA_TDEKJFJ_SHFJ_KKKK",
				"AXA_TDEKJFJ_SHFJ_LLLL", "AXA_TDEKJFJ_SHFJ_MMMM" };

		String newJobName = jobs + "";

		System.out.println("ALL JOBS " + newJobName);

	}

	public static String converDateToString(Date date) throws ServiceException {
		try {
			if (null != date) {
				SimpleDateFormat dataFormater = new SimpleDateFormat("dd/MM/yyyy");
				String stringobj = dataFormater.format(date);
				return stringobj;
			}

			return null;
		} catch (NullPointerException nullPointerEx) {
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

	public static String converDateToStringNotUI(Date date) throws ServiceException {
		try {
			if (null != date) {
				SimpleDateFormat dataFormater = new SimpleDateFormat("dd/MM/yy");
				String stringobj = dataFormater.format(date);
				return stringobj;
			}

			return null;
		} catch (NullPointerException nullPointerEx) {
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
	}

	public static Date converStringToDate(String strdate) {

		if (strdate.contains(".")) {
			strdate = strdate.substring(0, strdate.indexOf("."));
		}
		Date returndate = null;
		if (null != strdate) {
			try {
				SimpleDateFormat dataFormater = new SimpleDateFormat("MM/dd/yyyy");
				returndate = dataFormater.parse(strdate);
				dataFormater.format(returndate);
				return returndate;
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
		return returndate;
	}
	
	public static Date converStringToDateWithTime(String strdate) {

		if (strdate.contains(".")) {
			strdate = strdate.substring(0, strdate.indexOf("."));
		}
		Date returndate = null;
		if (null != strdate) {
			try {
				SimpleDateFormat dataFormater = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				returndate = dataFormater.parse(strdate);
				dataFormater.format(returndate);
				return returndate;
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		}
		return returndate;
	}

	public static Timestamp converStringTimeStampToSqlTimeStamp(String strdate) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a zzz");
			Date date = formatter.parse(strdate);
			timestamp = new Timestamp(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestamp;
	}
	
	
	public static Timestamp converStringTimeStampToSqlTimeStampWithOutSec(String strdate) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm");
			Date date = formatter.parse(strdate);
			timestamp = new Timestamp(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestamp;
	}
	
	public static Timestamp converStringTimeStampToSqlTimeStampWithHHMM(String strdate) {
		Timestamp timestamp = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm");
			Date date = formatter.parse(strdate);
			timestamp = new Timestamp(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestamp;
	}

	public static String converDateToStringToCal(Date date) throws ServiceException {
		try {
			if (null != date) {
				SimpleDateFormat dataFormater = new SimpleDateFormat("yyyy-MM-dd");
				String stringobj = dataFormater.format(date);
				return stringobj;
			}

			return null;
		} catch (NullPointerException nullPointerEx) {
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}

	}

	public static String usingDateAndCalendar(long input) {
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return (cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH) + "/"
				+ cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + (cal
					.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));

	}

	public static String usingDateAndCalendarWithTimeZone(long input) {
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		cal.setTime(date);
		return (cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH) + "/"
				+ cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":"
				+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + (cal
					.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));

	}

	public static String usingDateFormatter(long input) {
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss z");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);

	}

	public static String usingDateFormatterWithTimeZone(long input) {
		Date date = new Date(input);
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM/dd hh:mm:ss z");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);

	}

	public static String convertMilToMinandSec(String regexString) {
		int seconds = (int) (Long.parseLong(regexString) / 1000) % 60;
		int minutes = (int) ((Long.parseLong(regexString) / (1000 * 60)) % 60);
		if (0 == minutes) {
			return seconds + " Sec";
		} else {
			return minutes + " Min " + seconds + " Sec";
		}

	}
}

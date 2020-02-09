package com.itap.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * <p>
 * <b> TODO : Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class DateUtility {

	/** The LOGGER. */

	/**
	 * Gets the current db time stamp.
	 *
	 * @return the current db time stamp
	 */
	public static Timestamp getCurrentDBTimeStamp() {
		final java.util.Date date = new java.util.Date();
		return new Timestamp(date.getTime());
	}

	/**
	 * Gets the current time val.
	 *
	 * @return the current time val
	 */
	public static String getCurrentTimeVal() {
		final Date date = new Date(System.currentTimeMillis());
		final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		calendar.setTime(date);

		String startTimeValHrStr = "";
		String startTimeValMinStr = "";
		String startTimeValSecStr = "";
		final int startTimeValHr = calendar.get(Calendar.HOUR_OF_DAY);
		final int startTimeValMin = calendar.get(Calendar.MINUTE);
		final int startTimeValSec = calendar.get(Calendar.SECOND);

		if (startTimeValHr < 10) {
			startTimeValHrStr = startTimeValHrStr + "0" + String.valueOf(startTimeValHr);
		} else {
			startTimeValHrStr = startTimeValHrStr + String.valueOf(startTimeValHr);
		}
		if (startTimeValMin < 10) {
			startTimeValMinStr = startTimeValMinStr + "0" + String.valueOf(startTimeValMin);
		} else {
			startTimeValMinStr = startTimeValMinStr + String.valueOf(startTimeValMin);
		}
		if (startTimeValSec < 10) {
			startTimeValSecStr = startTimeValSecStr + "0" + String.valueOf(startTimeValSec);
		} else {
			startTimeValSecStr = startTimeValSecStr + String.valueOf(startTimeValSec);
		}
		final String startTimeVal = startTimeValHrStr + ":" + startTimeValMinStr + ":" + startTimeValSecStr;
		return startTimeVal;
	}

	/**
	 * Gets the time stamp.
	 *
	 * @param timeStamp
	 *            the time stamp
	 * @return the time stamp
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Timestamp getTimeStamp(final String timeStamp) throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		final java.util.Date date = sdf.parse(timeStamp);
		final java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		return timestamp;
	}

	/**
	 * Gets the diff in seconds.
	 *
	 * @param timestamp1
	 *            the timestamp1
	 * @param timeStamp2
	 *            the time stamp2
	 * @return the diff in seconds
	 */
	public static long getDiffInSeconds(final Timestamp timestamp1, final Timestamp timeStamp2) {
		final long nanosecond = timestamp1.getTime() - timeStamp2.getTime();
		return nanosecond / 1000;
	}

	/**
	 * Gets the diff in minute.
	 *
	 * @param timestamp1
	 *            the timestamp1
	 * @param timeStamp2
	 *            the time stamp2
	 * @return the diff in minute
	 */
	public static long getDiffInMinute(final Timestamp timestamp1, final Timestamp timeStamp2) {
		final long second = (timestamp1.getTime() - timeStamp2.getTime()) / 1000;
		return second / 60;
	}

	/**
	 * Gets the current date.
	 *
	 * @param dateFormat
	 *            the date format
	 * @return the current date
	 */
	public static String getCurrentDate(final String dateFormat) {
		final Calendar cal = Calendar.getInstance();
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * Gets the system info.
	 *
	 * @return the system info
	 */
	public static String getSystemInfo() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (final UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

	/**
	 * Gets the system mac info.
	 *
	 * @return the system mac info
	 */
	public static String getSystemMacInfo() {
		InetAddress ip;
		final StringBuilder sb = new StringBuilder();
		try {
			ip = InetAddress.getLocalHost();
			final NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			final byte[] mac = network.getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], i < mac.length - 1 ? "-" : ""));
			}
		} catch (final UnknownHostException e) {
			e.printStackTrace();
		} catch (final SocketException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

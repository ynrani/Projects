package com.itap.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.itap.constant.ITAPConstants;
import com.itap.exception.ServiceException;

public class CalendarDateUtils {

	

	public static List<String> getMonthCalDates(String todaysDate11) throws ServiceException {
		try {
			List<String> monthDays = new ArrayList<String>();

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
			Date todaysDate = new Date();
			if (null != todaysDate11) {
				todaysDate = dateFormat.parse(todaysDate11);
			}

			String startDt = dateFormat.format(todaysDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(todaysDate);
			cal.add(Calendar.DATE, 30);
			Date endDate = cal.getTime();
			String endDt = dateFormat.format(endDate);

			Date d1 = null;
			Date d2 = null;
			d1 = dateFormat.parse(startDt);
			d2 = dateFormat.parse(endDt);

			long diff = d2.getTime() - d1.getTime();
			diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			for (int i = 0; i <= diff; i++) {
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(d1);
				cal2.add(Calendar.DATE, i);
				Date x = cal2.getTime();
				String yy = dateFormat.format(x);
				monthDays.add(yy);
			}

			return monthDays;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException nullPointerEx) {
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
		return null;
	}

	public static String getPreviousMonthDate(String todaysDate11) throws ServiceException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
			Date todaysDate1 = new Date();
			Date todaysDate = new Date();
			if (null != todaysDate11) {
				todaysDate = dateFormat.parse(todaysDate11);
			}
			String startDt = dateFormat.format(todaysDate1);
			Calendar cal = Calendar.getInstance();
			cal.setTime(todaysDate);
			cal.add(Calendar.DATE, -30);
			Date endDate = cal.getTime();
			String endDt = dateFormat.format(endDate);
			if (startDt.equalsIgnoreCase(todaysDate11)) {
				endDt = null;
			}
			return endDt;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException nullPointerEx) {
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
		return null;
	}

	public static List<String> getDatesBetween(String startDt, String endDt)
			throws ServiceException {
		try {
			List<String> monthDays = new ArrayList<String>();

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

			Date d1 = format.parse(startDt);
			Date d2 = format.parse(endDt);

			long diff = d2.getTime() - d1.getTime();
			diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			for (int i = 0; i <= diff; i++) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(d1);
				cal.add(Calendar.DATE, i);
				Date x = cal.getTime();
				String yy = format.format(x);
				monthDays.add(yy);
			}

			return monthDays;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (NullPointerException nullPointerEx) {
			throw new ServiceException(ITAPConstants.NULL_POINTER_EXCEPTION, nullPointerEx);
		} catch (Exception otherEx) {
			throw new ServiceException(ITAPConstants.SERVICE_EXCEPTION, otherEx);
		}
		return null;
	}

}

package com.itap.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateService {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

	public static Date getDateFromJobStatus(String dialyJobStatus) {
		String jobDate = dialyJobStatus.substring(0,dialyJobStatus.lastIndexOf("$"));
		
		Date date = null;
		try {
			date = dateFormat.parse(jobDate);
			return date;
		}
		catch(Exception e) {
			return date;
		}
				
	}
	
}

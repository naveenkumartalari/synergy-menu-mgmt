/**
 * 
 */
package com.orbc.syn.menumgmt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ntalari
 *
 */
public class DateUtil {
	
	public static boolean isTokenExpired(String tokenExpDate) {
		//SimpleDateFormat format = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		Date date1 = null;
		Date date2 = null;
		try {
			String currDate=format.format(new Date());
			date1 = format.parse(currDate);
			date2 = format.parse(tokenExpDate);
		} catch (ParseException e) {
			throw new RuntimeException("date parsing exception",e);
		}
		return date1.compareTo(date2)<0;
	}
	
}

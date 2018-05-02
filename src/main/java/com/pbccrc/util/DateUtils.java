package com.pbccrc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private final static  SimpleDateFormat YMD_SF=new SimpleDateFormat("yyyy-MM-dd");
	private final static  SimpleDateFormat YMDHMS_SF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String getStrOfToday() {
		return  YMD_SF.format(new Date());
	}
	
}

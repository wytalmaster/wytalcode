package com.wytal.util.binder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;

public class DateBinder {
	private static DateFormat dateTime 	= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
	private static DateFormat date 		= new SimpleDateFormat("yyyy-MM-dd"); 
	protected static final Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.UTIL_LOGGER);
	public static Date unmarshalDate(String value) {
	    if (value == null || value.length() == 0) {
	        return null;
	    }
	    try {
	       return  date.parse(value);
	    } catch (Exception e) {
	        logger.error("Error parsing the input date format" + value,e);
	        return null;
	    } 

	}

	public static  String marshalDate(Date value) {
	    if (value == null) {
	        return null;
	    }
	
	    return date.format(value.getTime());
	}  
	
	
	public static  String marshalDateTime(Date value) {
	    if (value == null) {
	        return null;
	    }
	
	    return dateTime.format(value.getTime());
	} 


	public static Date unmarshalDateTime(String value) {
	    if (value == null || value.length() == 0) {
	        return null;
	    }
	
	    try {
	        return dateTime.parse(value);
	    } catch (Exception e) {
	    	logger.error("Error parsing the input date format" + value,e);
	        return null;
	    } 
	   
	}

}
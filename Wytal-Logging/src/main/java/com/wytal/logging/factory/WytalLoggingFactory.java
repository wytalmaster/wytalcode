package com.wytal.logging.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WytalLoggingFactory {
	
	/**
	 * Add project entries here
	 */
	public static final String UTIL_LOGGER  	= "util";
	public static final String COMMON_LOGGER  	= "commons";
	public static final String AUTH_LOGGER  	= "authentication";
	

	static Map<String, Logger> mapLogMap = new HashMap<>();
	static public Logger getLogger(String prj) {
		Logger logger = mapLogMap.get(prj);
		if ( logger==null ) {
			logger = LoggerFactory.getLogger(prj);
			mapLogMap.put(prj, logger);
		}
		return logger;
	}
	
	
}

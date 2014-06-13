package com.wytal.util.resource;


import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;



public class ResourceLoader {
	protected static final Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.UTIL_LOGGER);
	public  Properties loadProperty(Class<?> c,String resource) throws Exception{
		InputStream is = null;
		try {
			is = c.getResourceAsStream(resource);
			if(is != null){
				Properties p = new Properties();
				p.load(is);
				return p;
			}
		}
		catch(Exception ex){
			logger.error("",ex);
			throw ex;
		}
		finally {
			try { is.close();} catch(Exception ex){}
		}
		return null;
	}
	
	
	public Properties loadProperty(Class<?> c,String parent,String resource) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("/").append(parent).append("/");
		sb.append(System.getProperty("DB")).append("/");
		sb.append(resource);
		return this.loadProperty(c, sb.toString());
	}

}


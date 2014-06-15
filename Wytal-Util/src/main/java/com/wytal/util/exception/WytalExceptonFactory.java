package com.wytal.util.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.util.service.ServiceBase.ServiceResource;

public class WytalExceptonFactory extends ServiceResource {
    protected static final Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.UTIL_LOGGER);
    
    //Load all Exceptions
    private Map<String, String> exceptionMap;
    public void init() throws Exception{
    	    super.init();
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                    exceptionMap = new HashMap<>();
                    String sql = prop.getProperty("101");
                    conn= this.getConnection().getWytalDataSource();
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while(rs.next()){
                            String type = rs.getString(1);
                            String code = String.format("%04d", rs.getInt(2));
                            //String level = rs.getInt(3);
                            String desc = rs.getString(3);
                            exceptionMap.put(type+code, desc);
                    }
                    prop = null;
            }
            catch(Exception ex){
            		ex.printStackTrace();
                    logger.error("",ex);
            }
            finally{
                    this.getConnection().releaseResources(rs, ps,conn);
            }
    }


    //Get Exception
    public WytalException get(String errorCode){
            String description = exceptionMap.get(errorCode);
            if(description == null){
                    //Default Error and add warnings
            }
            WytalException ce =  new WytalException(errorCode);
            ce.description  = description;
            return ce;
    }

    public  WytalException get(String errorCode,String description){
            WytalException ce = this.get(errorCode);
            ce.details = description;
            return ce;
    }
    public  WytalException get(String errorCode,long arg0){
            WytalException ce = this.get(errorCode);
            ce.details = String.valueOf(arg0);
            return ce;

    }

    public WytalException get(String errorCode,String description,StackTraceElement[] ste){
            WytalException ce = new WytalException(errorCode);
            ce.details = description;
            ce.setStackTrace( ste);
            return ce;
    }

    public WytalException get(String errorCode,StackTraceElement[] ste){
            WytalException ce = new WytalException(errorCode);
            ce.setStackTrace( ste);
            ce.details ="";
            return ce;
    }


	@Override
	protected Logger getLogger() {
		return logger;
	}



}

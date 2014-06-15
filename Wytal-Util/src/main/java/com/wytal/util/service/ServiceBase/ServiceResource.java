package com.wytal.util.service.ServiceBase;

import java.util.Properties;

import org.slf4j.Logger;

import com.wytal.util.db.ConnectionLookup;
import com.wytal.util.resource.ResourceLoader;

public abstract class ServiceResource {
	private ConnectionLookup connection;
    public void setConnection(ConnectionLookup lookup){
            this.connection = lookup;
    }
	private ResourceLoader resourceLoader;
    
    public void setResourceLoader(ResourceLoader loader){
    	this.resourceLoader = loader;
    }
    
    protected  ResourceLoader getResourceLoader(){
    	return this.resourceLoader;
    }
    
    protected ConnectionLookup getConnection(){
    	return this.connection;
    }
    
    private String resourceName;
    public void setResourceName(String name){
    	this.resourceName = name;
    }
    
    protected String getResourceName(){
    	return this.resourceName;
    }
    
    
    private String resourceFolder="jdbc";
    protected String getResourceFolder(){
    	return this.resourceFolder;
    }
    
    public void setResourceFolder(String folder){
    	this.resourceFolder = folder;
    }
    
    protected Properties prop;
    
    protected abstract Logger getLogger();
    
    public void init() throws Exception{
		try {
			prop = this.getResourceLoader().loadProperty(this.getClass(), this.getResourceFolder(), this.getResourceName());
			if(prop==null){
				System.out.println("Cannot find the resource "+ this.getResourceName());
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			this.getLogger().error(ex.getMessage(),ex);
			throw ex;
		}
	}

}

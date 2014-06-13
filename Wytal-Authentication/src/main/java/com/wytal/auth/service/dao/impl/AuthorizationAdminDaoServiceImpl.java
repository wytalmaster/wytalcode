package com.wytal.auth.service.dao.impl;

import org.slf4j.Logger;

import com.wytal.auth.service.dao.AuthorizationAdminDaoService;
import com.wytal.logging.factory.WytalLoggingFactory;
import com.wytal.util.service.ServiceBase.ServiceResource;

public class AuthorizationAdminDaoServiceImpl  extends ServiceResource implements AuthorizationAdminDaoService{
	protected Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.AUTH_LOGGER);

	@Override
	protected Logger getLogger() {
		// TODO Auto-generated method stub
		return logger;
	}
	
	
}

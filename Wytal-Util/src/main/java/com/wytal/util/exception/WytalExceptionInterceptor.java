package com.wytal.util.exception;
import java.util.Iterator;
import java.util.List;

import org.apache.cxf.binding.xml.interceptor.XMLFaultOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.interceptor.MessageSenderInterceptor;
import org.apache.cxf.interceptor.StaxOutInterceptor;
import org.apache.cxf.jaxrs.interceptor.JAXRSOutInterceptor;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;


public class WytalExceptionInterceptor  extends  JAXRSOutInterceptor  {
	protected static final Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.UTIL_LOGGER);
    protected WytalExceptonFactory factory;
    public void setExceptionFactory(WytalExceptonFactory factory){
            this.factory = factory;
    }
    private boolean handleMessageCalled;
	public WytalExceptionInterceptor() {
	    this.getAfter().add(MessageSenderInterceptor.class.getName());
	    this.getBefore().add(XMLFaultOutInterceptor.class.getName());
	    this.getBefore().add(StaxOutInterceptor.class.getName());
	}

	@Override
	public void handleFault(Message message) throws Fault{
	    logger.debug("Receoved a fault .....................");
	}

	@Override
	public void handleMessage(Message message) throws Fault {
	    WytalException we  = null;
	    handleMessageCalled = true;
	    Exception ex = message.getContent(Exception.class);
	    if (ex == null) {
	        throw new RuntimeException("Exception is expected");
	    }
	    if (!(ex instanceof Fault)) {
	        throw new RuntimeException("Fault is expected");
	    }
	    try {
	            we =  (WytalException)(((Fault)ex).getCause());
	            if(we==null){
	                    logger.error("WT9999:FIX THIS ASAP, Unhandled Exception" + "Thrown without a valid Wytal exception");
	                    we = this.factory.get("WT0001");
	            }
	    }
	    catch(Exception c){
	            logger.error("WT9999:FIX THIS ASAP, Unhandled Exception" + "Thrown without a valid CAS exception",c);
	            we = this.factory.get("WT9999");
	    }
	
	    javax.ws.rs.core.Response response = JAXRSUtils.convertFaultToResponse(we, message.getExchange().getInMessage());
	    message.setContent(List.class, new MessageContentsList(response));
	
	
	    message.getExchange().setOutMessage(message.getExchange().getOutFaultMessage());
	    super.handleMessage(message);
	
	
	    Iterator<Interceptor<? extends Message>> chainIterator = message.getInterceptorChain().iterator();
	            while (chainIterator.hasNext()) {
	                    Interceptor<? extends Message> interceptor = chainIterator.next();
	                    // removing for xml
	                    if (interceptor instanceof XMLFaultOutInterceptor) {
	                            message.getInterceptorChain().remove((XMLFaultOutInterceptor) interceptor);}
	                    if (interceptor instanceof StaxOutInterceptor) {
	                        message.getInterceptorChain().remove((StaxOutInterceptor) interceptor);
	                }
	
	        }
	
	}
	protected boolean handleMessageCalled() {
		return handleMessageCalled;
	}

}

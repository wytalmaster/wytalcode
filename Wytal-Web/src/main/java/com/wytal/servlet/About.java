package com.wytal.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

	
	public class About extends HttpServlet {

        private static final long serialVersionUID = 1L;

        /**
         * The doGet method of the servlet. <br>
         *
         * This method is called when a form has its tag value method equals to get.
         *
         * @param request the request send by the client to the server
         * @param response the response send by the server to the client
         * @throws ServletException if an error occurred
         * @throws IOException if an error occurred
         */
        public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

//              LoggerContext lc = new LoggerContext();
//              Logger l = lc.getLogger(this.getClass().getName());
//              l.error("First log out....");

                org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
            logger.error("Inside About Servlet. "+ new Date
            		());
            logger.debug("Catalina home "+ System.getProperty("jetty.home"))    ;
            logger.debug("org.apache.cxf.Logger "+ System.getProperty("org.apache.cxf.Logger")) ;
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

            StatusPrinter .print(lc);
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            out.println("<HTML>");
            out.println("  <HEAD><TITLE>Wytal </TITLE></HEAD>");
            out.println("  <BODY>");
            out.print("<h3 style=\"color:#0000ff\">Wytal</h3>");
            out.println("<br/>");
            out.println("<table border=\"0\">");
            out.println("<tr>");
            out.println("<td>Available Memory :</td>");
            out.println("<td>"+Runtime.getRuntime().freeMemory()+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Available Processors :</td>");
            out.println("<td>"+Runtime.getRuntime().availableProcessors()+"</td>");
            out.println("</tr>");

            out.println("</table>");

            //out.println("<embed width=\"420\" height=\"345\" src=\"http://www.youtube.com/watch?v=bTy9gC8BAmQ\"type=\"application/x-shockwave-flash\"></embed>");

            out.println("  </BODY>");
            out.println("</HTML>");
            out.flush();
            out.close();

        }

}

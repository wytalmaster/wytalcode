package com.wytal.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;

import com.wytal.logging.factory.WytalLoggingFactory;



public class ConnectionLookup {
    private static final String WYTALDATASOURCE = "jdbc/wytal";
    private static Map<String, DataSource> dataSoures  = new HashMap<>();
    protected static final Logger logger = WytalLoggingFactory.getLogger(WytalLoggingFactory.UTIL_LOGGER);


    public void init() {

        Context initialContext = null;
        try {
            initialContext = new InitialContext();
            cacheDataSource(initialContext, WYTALDATASOURCE);
        } catch(Exception ex){
        	logger.error("",ex);
            logger.error("Error in loading the initial context",ex);
        } finally {
            closeQuietly(initialContext);
        }
    }

    private static void cacheDataSource(Context initialContext, String datasource){
        try{
            DataSource ds = (DataSource)initialContext.lookup(datasource);
            if(ds != null){
                dataSoures.put(datasource,ds);
            }
        }
        catch(NamingException ne){
            logger.error("Failed to lookup datasource: " + datasource);
        }
    }
    /*
      * This method public only for test/dev code. (so can use alternate connection via Spring config).
      * production code must used defined getXzyDataSource() from below.
      */
    public static Connection getConnection(String source){
        try {
        	boolean track = false;
            if(track){
                try {
                    StackTraceElement[] ste = Thread.currentThread().getStackTrace();
                    if(ste !=null && ste.length>4){
                        StackTraceElement s = ste[3];
                        System.out.println("Call to create connection " + source + "----"+ s.getClassName()+":"+ s.getMethodName()+":"+s.getLineNumber());
                    }
                } catch (Exception e) {
                  //ignore

                }
            }
            
            DataSource ds = dataSoures.get(source);
            if(ds != null){
                return ds.getConnection();
            }
            else {
                logger.error("Failed to lookup datasource: " + source);
                return null;
            }

        }
        catch(SQLException ex){
            logger.error("Cannot get connection SQLException: " + source + ":" ,ex);
        }
        catch(Exception ex){
            logger.error("Cannot get connection: " + source ,ex);
        }
        return null;

    }



    public Connection getWytalDataSource(){
        return getConnection(WYTALDATASOURCE);
    }
    
   

    /**
     * Closed a JNDI context while ignoring any exceptions.
     *
     * @param context a JNDI context to close.
     */
    private static void closeQuietly(final Context context) {

        if (context == null) {
            return;
        }

        try {
            context.close();
        } catch (Exception ignored) {

        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    // -------------------- Utility Methods ---------------------//
    public  void closeConnection(Connection con) {
        if(con != null) {
                try {
                        con.close();
                } catch (SQLException e) {
                        //logger.error("closeConnection "+e.toString());
                }
        }
    }

    public  void closeStatement(Statement stmt) {
        if(stmt != null) {
                try {
                        stmt.close();
                } catch (SQLException e) {
                        //logger.error("closeStatement "+e.toString());
                }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if(rs != null) {
                try {
                        rs.close();
                } catch (SQLException e) {
                        //logger.error("closeResultSet "+e.toString());
                }
        }
    }

    public static void commit(Connection con) {
        if(con != null) {
                try {
                        if (!con.isClosed() && !con.getAutoCommit())
                                con.commit();
                } catch (SQLException e) {
                        //logger.error("commit "+e.toString());
                }
        }
    }
    public static void rollback(Connection con) {
        if(con != null) {
                try {
                        con.rollback();
                } catch (SQLException e) {
                        //logger.error("rollback "+e.toString());
                }
        }
    }

    public  void releaseResources(Statement st, Connection conn) {
        closeStatement(st);
        closeConnection(conn);
    }

    public void releaseResources(ResultSet rs, Statement st, Connection conn) {
        closeResultSet(rs);
        closeStatement(st);
        closeConnection(conn);
    }

    public  void commitAndClose(ResultSet rs, Statement st, Connection conn) {
    	commit(conn);
        closeResultSet(rs);
        closeStatement(st);
        closeConnection(conn);
    }


}

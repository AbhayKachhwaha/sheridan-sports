package com.sheridansports.listeners;

import com.prog32758.db.DBConnection;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * A ServletContextListener that will get the database configuration from
 * the ServletContext and instantiate a new DBConnection object responsible for
 * getting Connection objects to the database.
 */
public class DBConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Get the database configuration from the ServletContext
        ServletContext sc = sce.getServletContext();
        String driver = sc.getInitParameter("driver");
        String url = sc.getInitParameter("url");
        String database = sc.getInitParameter("database");
        String dbusername = sc.getInitParameter("dbusername");
        String dbpassword = sc.getInitParameter("dbpassword");
        
        DBConnection dbConnection = new DBConnection(driver, url, database,
                dbusername, dbpassword);
        
        // Store the DBConnection object in the ServletContext
        sc.setAttribute("dbConnection", dbConnection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Ignore this event
    }
}

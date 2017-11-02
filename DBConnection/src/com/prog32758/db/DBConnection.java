/*
 * A class used to establish JDBC Connections.
 * 
 * Justin Alerta
 * February 2015
 */

package com.prog32758.db;

import java.sql.*;

/**
 *
 * @author Justin
 */
public class DBConnection {
    private final String url;
    private final String username;
    private final String password;
    
    /*
     * Loads the driver class and set the required properties when establishing
     * a Connection.
     */
    public DBConnection(String driver, String url, String database,
            String username, String password) {
        
        try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("ERROR: Exception loading driver class");
        }

        this.url = url + database;
        this.username = username;
        this.password = password;
    }

    /*
     * Method that outside classes will use to get a Connection object.
     * Note that if this method causes an SQLException, it is thrown to the
     * class that called getConnection().
     */    
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Exception creating Connection object");
        }
        
        return conn;

    }
    
    public static void closeJDBCObjects(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            
            if (stmt != null) {
                stmt.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ignored) {
        }
    }
    
    public static void closeJDBCObjects(Connection conn, Statement stmt) {
        closeJDBCObjects(conn, stmt, null);
    }
}

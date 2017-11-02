/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sheridansports.dao;

import com.prog32758.db.DBConnection;
import com.sheridansports.business.Product;
import com.sheridansports.business.Purchase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PurchaseDAO {
    
    public PurchaseDAO(){
        
    }
    
    //method to add a record in the purchase table
    public boolean addPurchase(Connection conn, int cardId){
        boolean success = false;
        PreparedStatement ps =null;
        
        try{
            String sql = "INSERT INTO sheridansports.purchase (CardId) VALUES (?);";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cardId);
            int result = ps.executeUpdate();
            
            if(result > 0){
                success = true;
            }
        }
        
        catch(SQLException e){
            System.out.println("addPurchase SQL Exception" + e.getMessage());
            System.out.println("CardId:" + cardId);
            DBConnection.closeJDBCObjects(conn, ps);
        }
        
        
        return success;
    }
    
    //Method to add a record in the purchaseItem table
    public boolean addPurchaseItem(Connection conn, int purchaseId, String productId, int quantity, double price){
        boolean success = false;
        
        PreparedStatement ps = null;
        
        try{
            String sql = "INSERT INTO Purchaseitem (PurchaseId, ProductId, Quantity, Price) VALUES (? , ? , ? , ?);";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, purchaseId);
            ps.setString(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            int result = ps.executeUpdate();
            
            if(result > 0){
                success = true;
            }
        }
        catch(SQLException e){
            System.out.println("addPurchaseItem SQL Exception" + e.getMessage());
            DBConnection.closeJDBCObjects(conn, ps);
        }
        
        return success;
    }
    
    //Method to get the purchaseId from purchase table by cardId to forward the output to addPurchaseItem
    public int getPurchaseId(Connection conn, int cardId){
        int purchaseId = 0;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "Select purchaseId from Purchase WHERE cardId = ? ;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cardId);
            rs = ps.executeQuery();
            rs.next();
            
            Purchase purchase = new Purchase();
            purchaseId = rs.getInt("purchaseId");
            
            if(purchaseId == 0){
                System.out.println("Unsuccessfull");
            }
            else{
                purchase.setPurchaseId(purchaseId);
            }
        }
        catch(SQLException e){
            System.out.println("getPurchaseId SQL Exception" + e.getMessage());
            DBConnection.closeJDBCObjects(conn, ps, rs);
        }
        return purchaseId;
    }
    
    //Method to close all jdbc the objects
    public boolean closeAllObjects(Connection conn, PreparedStatement ps, ResultSet rs){
        boolean success = true;
        DBConnection.closeJDBCObjects(conn, ps, rs);
        return success;
    }
    
    //Another method to close the connection objects and such
    public boolean closeConnectionObject(Connection conn){
        boolean success = true;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "";
            ps = conn.prepareStatement(sql);
        }
        catch(SQLException e){
            DBConnection.closeJDBCObjects(conn, ps);
        }
        finally{
            DBConnection.closeJDBCObjects(conn, ps);
            closeAllObjects(conn, ps, rs);
        }
        return success;
    }
}

package com.sheridansports.dao;

import com.prog32758.db.DBConnection;
import com.sheridansports.business.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A data access object (DAO) responsible for querying with the Product table of
 * the SheridanSports database.
 *
 */

public class ProductDAO {

    public ProductDAO() {
    }

    /**
     * Get a Product bean based on a matching Product ID.
     *
     * @param conn the connection to the SheridanSports database
     * @param productId the Product ID of the Product to retrieve
     * @return a Product JavaBean
     */
    
    //Gets a record of product by using the unique productId
    public Product getProductById(Connection conn, String productId) {
        Product product = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Product WHERE productId = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Get the product information
                String manufacturer = rs.getString("Manufacturer");
                String item = rs.getString("Item");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                boolean available = rs.getBoolean("Available");
                
                // Set the null product variable as a new Product and set the
                // value of each property
                product = new Product();
                product.setProductId(productId);
                product.setManufacturer(manufacturer);
                product.setItem(item);
                product.setDescription(description);
                product.setPrice(price);
                product.setAvailable(available);
            }
        } catch(SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
        } finally {
            // Close JDBC objects
            DBConnection.closeJDBCObjects(conn, ps, rs);
        }
        // Return a product bean (this will be null if there is no product
        // with the specified ID)
        return product;
    }
    //Method that retrieves all records from product table
    public ArrayList<Product> getAllProducts(Connection conn) {
        ArrayList<Product> products = new ArrayList();
        
        //Declare JDBC Objects
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //Define the query to retrieve all products
            String sql= "Select * from Product WHERE Available = 1;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //Process the query results
            while (rs.next()) {
                //Get the values of all fields for current product
                String productId = rs.getString("ProductId");
                String manufacturer = rs.getString("Manufacturer");
                String item = rs.getString("Item");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                boolean available = rs.getBoolean("Available");
                
                //Create a product bean based on the above values
                Product p = new Product();
                p.setProductId(productId);
                p.setManufacturer(manufacturer);
                p.setItem(item);
                p.setDescription(description);
                p.setPrice(price);
                p.setAvailable(available);
                
                //Add the product bean to the arraylist
                products.add(p);
                
            }
        }
        catch(SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        } 
        finally {
            DBConnection.closeJDBCObjects(conn, ps, rs);
        }
        return products;
    }


    //method to add a product to the database
    //returns true if product is added to the database
    public boolean addProduct(Connection conn, String productId, String manufacturer, String item, String description, float price, boolean available){
        
        boolean success = false;
        
        PreparedStatement ps = null;  
        
        try{
            String sql = "INSERT INTO Product (ProductId, Manufacturer, Item, Description, Price, Available) VALUES (? , ? , ? , ? , ? , ?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            ps.setString(2, manufacturer);
            ps.setString(3, item);
            ps.setString(4, description);
            ps.setFloat(5, price);
            ps.setBoolean(6, available);
            
            int numOfRecordsUpdated = ps.executeUpdate();
            if(numOfRecordsUpdated > 0){
                success = true;
            }
            
        }
        
        catch(SQLException e){
            System.out.println("SQL Exception" + e.getMessage());
        }
        
        finally{
            DBConnection.closeJDBCObjects(conn, ps);
        }
        
        return success;
    }
    
    //Method to editProducts in the database
    public boolean editProduct(Connection conn, Product product){
        boolean success = false;
        
        
        PreparedStatement ps = null;
        
        try{
            String sql = "UPDATE Product SET Manufacturer = ? , Item = ? , Description = ? , Price = ? , Available = ? WHERE ProductId = ?;";
            ps = conn.prepareStatement(sql);
            
            
            //Accessing all the variable in Product JavaBean through getter methods
            
            ps.setString(1, product.getManufacturer());
            ps.setString(2, product.getItem());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setBoolean(5, product.isAvailable());
            ps.setString(6, product.getProductId());
            
            int numOfRecordsUpdated = ps.executeUpdate();
            if(numOfRecordsUpdated > 0){
                success = true;
            }
        }
        
        catch(SQLException e){
            System.out.println("SQL Exception" + e.getMessage());
        }
        
        finally{
            DBConnection.closeJDBCObjects(conn, ps);
        }
        
        return success;
    }
}

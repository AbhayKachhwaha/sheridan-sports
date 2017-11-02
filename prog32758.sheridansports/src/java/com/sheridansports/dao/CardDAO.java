
package com.sheridansports.dao;

import com.prog32758.db.DBConnection;
import com.sheridansports.business.Card;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CardDAO {
    
    public CardDAO(){
        
    }
    //Gets a list of all cards stored in the database
    public ArrayList<Card> getAllCards(Connection conn) {
        //Declare a variable to hold the return value
        ArrayList<Card> cards = new ArrayList();
        PreparedStatement ps = null;
        ResultSet rs= null;
        try { 
            String sql = "Select * from Card;";
            ps = conn.prepareStatement(sql);
            //TroubleShooting tip: you can print ur query just before it executes. Copy the query printed in the tomcat output and paste it in workbench
            rs= ps.executeQuery();
            while (rs.next()) {
                //Get the values for the current record
                System.out.println(ps.toString());
                int cardId= rs.getInt("CardId");
                String cardNumber = rs.getString("CardNumber");
                String cardType = rs.getString("CardType");
                String expiryMonth = rs.getString("ExpiryMonth");
                String expiryYear = rs.getString("ExpiryYear");
                String cardHolder = rs.getString("CardHolder");
                
                int userId = rs.getInt("UserId");
                //Set the values to the record
                Card c = new Card();
                c.setCardId(cardId);
                c.setCardNumber(cardNumber);
                c.setCardType(cardType);
                c.setExpiryMonth(expiryMonth);
                c.setExpiryYear(expiryYear);
                c.setCardHolder(cardHolder);
                c.setUserId(userId);
                
                //Add the card bean to the arrayList
                cards.add(c);
            }
        }
        catch(SQLException e) {
            System.err.println("SQLException:" + e.getMessage());
        }
        finally {
            DBConnection.closeJDBCObjects(conn, ps, rs);
        }
        return cards;
    }
    
    //Checks whether the card information matches the  cards in the database or not
    public boolean cardAuthentication(Connection conn, String cardNumber, String cardType, String expiryMonth, String expiryYear, String cardHolder ){
        boolean success = false;
        
        PreparedStatement ps = null;
        
        
        try{
            String sql = "SELECT * From card WHERE cardNumber = ? AND cardType = ? AND expiryMonth = ? AND expiryYear = ? AND cardHolder = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cardNumber);
            ps.setString(2, cardType);
            ps.setString(3, expiryMonth);
            ps.setString(4, expiryYear);
            ps.setString(5, cardHolder);
            success = ps.execute();
            ResultSet rs = ps.executeQuery();
            
        }
        
         catch(SQLException e){
            System.out.println("cardAuthentication SQL Exception" + e.getMessage());
            DBConnection.closeJDBCObjects(conn, ps);
        }
        
        
        return success;
    }
    
    //Gets the cardId to forward to cardAuthentication method for PurchaseSuccessfulServet
    public int getCardId(Connection conn, String cardNumber){
        int cardId = 0;
        
        PreparedStatement ps = null;
        
        try{
            String sql = "SELECT cardId FROM card WHERE cardNumber = ? ;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cardNumber);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Card card = new Card();
            
            cardId = rs.getInt("cardId");
            if(cardId == 0){
                System.out.println("Unsuccessfull");
            }
            else{
                card.setCardId(cardId);
            }
        }
        
        catch(SQLException e) {
            System.err.println("getCardId SQLException:" + e.getMessage());
            DBConnection.closeJDBCObjects(conn, ps);
        }
        
        
        return cardId;
    }
}

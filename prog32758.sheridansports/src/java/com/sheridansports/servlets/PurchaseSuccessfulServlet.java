
package com.sheridansports.servlets;

import com.prog32758.db.DBConnection;
import com.sheridansports.business.PurchaseItem;
import com.sheridansports.dao.CardDAO;
import com.sheridansports.dao.PurchaseDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PurchaseSuccessfulServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String cardNumber = request.getParameter("cardNumber");
        String cardType = request.getParameter("cardType");
        String expiryMonth = request.getParameter("expiryMonth");
        String expiryYear = request.getParameter("expiryYear");
        String cardHolder = request.getParameter("cardHolder");
        
        //Getting ServletContext object to acquire the connection
        ServletContext sc = this.getServletContext();
        // Get the session object for this user
        HttpSession session = request.getSession();
        
        DBConnection dbConnection = (DBConnection) sc.getAttribute("dbConnection");
        Connection conn = dbConnection.getConnection();
        
        //Get the shopping cart stored in the session for this user (ArrayList<PurchaseItem>)
        ArrayList<PurchaseItem> shoppingCart = (ArrayList<PurchaseItem>)session.getAttribute("cart");
        
        CardDAO cardDAO = new CardDAO();
        PurchaseDAO purchaseDAO = new PurchaseDAO();
        
        
        
        //Step 1: Check if the card information entered by the client meatches any record in the database
        boolean result = cardDAO.cardAuthentication(conn, cardNumber, cardType, expiryMonth, expiryYear, cardHolder);
        int cardId = cardDAO.getCardId(conn, cardNumber);
        if(result == true){
            //Step 2: Check if the transaction is recorded in the Purchase table
            boolean result1 = purchaseDAO.addPurchase(conn, cardId);
            if(result1){
                System.out.println("Purchase successfully recorded in the database");

                int purchaseId = purchaseDAO.getPurchaseId(conn, cardId);


                double grandTotal = (Double)session.getAttribute("grandTotal");


                int indexOfProduct;
                //Step 3: Add all the items in the cart to the database

                for(indexOfProduct =0; indexOfProduct<shoppingCart.size(); indexOfProduct++) {

                    PurchaseItem item = shoppingCart.get(indexOfProduct);
                    String productId = item.getProduct().getProductId();
                    item.getProduct().setProductId(productId);
                    int quantity = item.getQuantity();
                    double price = item.getPrice();

                    //Step4:Check if the transaction is recorded in the Purchase Item table
                    boolean result2 = purchaseDAO.addPurchaseItem(conn, purchaseId, productId, quantity, price);
                    if(result2){
                        System.out.println("PurchaseItem successfully recorded in the database");
                        System.out.println("Grand Total = " + grandTotal);
                        purchaseDAO.closeConnectionObject(conn);

                        //Step 5: Store the grandTotal in a variable for the client/customer and forward it to paymentsuccessful.jsp to display
                        double grandTotal1 = (Double)session.getAttribute("grandTotal");
                        request.setAttribute("grandTotal1", grandTotal1);
                        RequestDispatcher rd = request.getRequestDispatcher("paymentsuccessful.jsp");
                        rd.forward(request, response);
                        //Reset the session so client will not have to restet the application
                        session.invalidate();
                        response.sendRedirect("index.html");
                    }
                    //Close all the jdbc objects 
                    else{
                        System.out.println("addPurchaseItem unsuccessful");
                        purchaseDAO.closeConnectionObject(conn);
                    }
                }
            }
            else{
                System.out.println("addPurchase unsuccessful");
                purchaseDAO.closeConnectionObject(conn);
                System.out.println("Card Authentication fail");
                purchaseDAO.closeConnectionObject(conn);
                RequestDispatcher rd = request.getRequestDispatcher("paymentunsuccessful.jsp");
                rd.forward(request, response);
                //Reset the session so client will not have to reset the application
                session.invalidate();
                response.sendRedirect("index.html");
            }
        }
        else{
            System.out.println("Card Authentication fail");    
        }   
        
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

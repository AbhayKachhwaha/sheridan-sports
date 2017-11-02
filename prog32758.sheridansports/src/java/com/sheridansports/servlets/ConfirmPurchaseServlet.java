

package com.sheridansports.servlets;

import com.sheridansports.business.PurchaseItem;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ConfirmPurchaseServlet extends HttpServlet {
   
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
        
        
        
        //the total amount inclusive of all tax from confirmpurchase.jsp
        String grandTotal1 = request.getParameter("grandTotal");
        double grandTotal = Double.parseDouble(grandTotal1);
        
        // Get the session object for this user
        HttpSession session = request.getSession();
        
        //Get the shopping cart stored in the session for this user (ArrayList<PurchaseItem>)
        ArrayList<PurchaseItem> shoppingCart = (ArrayList<PurchaseItem>)session.getAttribute("cart");
        //Check if the shopping cart is empty or not
        if(shoppingCart == null){
            System.out.println("No items in the cart");
            RequestDispatcher rd = request.getRequestDispatcher("noitem.jsp");
            rd.forward(request, response);
            //Reset the session so client will not have to restart the application
            session.invalidate();
            response.sendRedirect("index.html");
        }
        
        else{
        
        //Store the grandTotal variable to the session object
        session.setAttribute("grandTotal", grandTotal);
        // Forward the request to checkout.jsp
        RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
        rd.forward(request, response);
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

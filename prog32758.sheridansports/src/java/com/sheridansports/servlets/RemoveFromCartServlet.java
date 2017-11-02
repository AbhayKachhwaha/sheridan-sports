

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


public class RemoveFromCartServlet extends HttpServlet {
   
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
        //Get the productId of the item to remove from the cart
        String productId = request.getParameter("productId");
        String price1 = request.getParameter("price");
        double price = Double.parseDouble(price1);
        
        
        // Get the session object for this user
        HttpSession session = request.getSession();
        
        //Get the shopping cart stored in the session for this user (ArrayList<PurchaseItem>)
        ArrayList<PurchaseItem> shoppingCart = (ArrayList<PurchaseItem>)session.getAttribute("cart");
        
        //Check if user has no shopping cart
        if(shoppingCart == null){
            //Create a new shopping cart
            shoppingCart = new ArrayList<PurchaseItem>();
        }
        
        
        //Check whether the the item is in the shopping cart
        int indexOfProduct;
        
        //Loop through the cart looking for matching productId
        for(indexOfProduct=0; indexOfProduct<shoppingCart.size(); indexOfProduct++){
            //Check if the current item has same ProductId
            PurchaseItem item = shoppingCart.get(indexOfProduct);
            String productIdInCart = item.getProduct().getProductId();
            if(productIdInCart.equals(productId)){
                //Remove 1 from the quantity of the item from the cart
                item.setQuantity(item.getQuantity()-1);
                //Set the price
                item.setPrice(price);
                
                //Nested if to see if the quantity of the item is zero after removing 1 from it
                if(item.getQuantity()==0){
                    shoppingCart.remove(indexOfProduct);
                }
                break;
            }
        }
        
        //Store the modified shopping cart back in the session
        session.setAttribute("cart", shoppingCart);
        
         //Forward the request to the list of products
        RequestDispatcher rd = request.getRequestDispatcher("confirmpurchase.jsp");
        rd.forward(request, response);
        
        
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



package com.sheridansports.servlets;

import com.sheridansports.business.PurchaseItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddToCartServlet extends HttpServlet {
       
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
        //Get the productId of the item to add to the cart
        String productId = request.getParameter("productId");
        //Get the price of the item to add to the cart
        String sPrice = request.getParameter("price");
        double price = Double.parseDouble(sPrice);
        
        // Get the session object for this user
        HttpSession session = request.getSession();
        
        //Get the shopping cart stored in the session for this user (ArrayList<PurchaseItem>)
        ArrayList<PurchaseItem> shoppingCart = (ArrayList<PurchaseItem>)session.getAttribute("cart");
        
        //Check if the user has no shopping cart i.e. the shopping cart variable is null
        if(shoppingCart == null) {
            //Create a new shopping cart
            shoppingCart = new ArrayList<PurchaseItem>();
        }
        
        //Check whether the product is already in the shopping cart
        int indexOfProduct;
        //Keep track as to whether the product is in the cart
        boolean inCart = false;
        //Loop through the cart, look for the matching productId
        for(indexOfProduct =0; indexOfProduct<shoppingCart.size(); indexOfProduct++) {
            //Check if the item at the current index has a matching productId
            PurchaseItem item = shoppingCart.get(indexOfProduct);
            String productIdInCart = item.getProduct().getProductId();
            if(productIdInCart.equals(productId)) {
                //We have found the matching productId so
                //1) Add 1 to the quantity
                item.setQuantity(item.getQuantity()+1);
                //2) Set the price
                item.setPrice(price);
                //3) Set the inCart flag to true
                inCart = true;
                //4)break out of the loop
                break;
            }
        }
        
        //If the product is not in the shopping cart add a new PurchaseItem to the ArrayList
        if(!inCart) {
        PurchaseItem newItem = new PurchaseItem();
        //Set the prodcut id 
        newItem.getProduct().setProductId(productId);
        //Set the quantity to 1
        newItem.setQuantity(1);
        //Set the current price of the product
        newItem.setPrice(price);
        //Add to the shopping cart
        shoppingCart.add(newItem);
        }
        //Store the modified shopping cart back in the session
        session.setAttribute("cart", shoppingCart);
        
        System.out.println("Shopping Cart Contents: ");
        //Print the shopping cart contents to the console
        for(int i=0; i<shoppingCart.size(); i++) {
            PurchaseItem pi = shoppingCart.get(i);
            System.out.println("Item: "+ pi.getProduct().getProductId());
            System.out.println("Quantity: "+ pi.getQuantity());
            System.out.println("Price: $"+ pi.getPrice());
        }
        //Forward the request to the list of products
        RequestDispatcher rd = request.getRequestDispatcher("GetProducts.do");
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

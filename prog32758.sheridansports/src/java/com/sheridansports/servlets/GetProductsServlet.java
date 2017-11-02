

package com.sheridansports.servlets;

import com.prog32758.db.DBConnection;
import com.sheridansports.business.Product;
import com.sheridansports.dao.ProductDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetProductsServlet extends HttpServlet {
       
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
        // Get the DBConnection object that stored in the servlet context (via the DBConnectionListener)
        ServletContext sc = this.getServletContext();
        DBConnection dbConnection = (DBConnection)sc.getAttribute("dbConnection");
        
        //Establish a connection to the database
        Connection conn = dbConnection.getConnection();
        
        //Get a list of all products using ProductDAO
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> products = productDAO.getAllProducts(conn);
        
        //Store the arraylist as an attribute of the request so that the jsp can access and display them
        
        request.setAttribute("products", products);
        
        //Forward the request to productList.jsp which will display a table of all products
        RequestDispatcher rd = request.getRequestDispatcher("productlist.jsp");
        rd.forward(request, response);
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
        doGet(request, response);
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

package com.sheridansports.servlets;

import com.prog32758.db.DBConnection;
import com.sheridansports.business.Product;
import com.sheridansports.dao.ProductDAO;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is responsible for returning information on a product based on
 * the product ID.
 */
public class ViewProductInfoServlet extends HttpServlet {
   

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
        // Get the product ID from the request
        String productId = request.getParameter("productId");
        
        // Get the DBConnection object from the ServletContext
        ServletContext sc = this.getServletContext();
        DBConnection dbConnection = (DBConnection)sc.getAttribute("dbConnection");
        
        // Establish a Connection to the SheridanSports database using DBConnection
        Connection conn = dbConnection.getConnection();
        
        // Use the ProductDAO to get the Product with the matching ID
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(conn, productId);
        
        // Store the Product bean as an attribute of the request
        request.setAttribute("product", product);
        
        // Forward the request to viewproductinfo.jsp
        RequestDispatcher rd = request.getRequestDispatcher("viewproductinfo.jsp");
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

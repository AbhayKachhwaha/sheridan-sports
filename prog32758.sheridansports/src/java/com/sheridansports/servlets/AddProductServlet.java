/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sheridansports.servlets;

import com.prog32758.db.DBConnection;
import com.sheridansports.dao.ProductDAO;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddProductServlet extends HttpServlet {
   

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
        
        //All the varibles required to be added to the Product table
        String productId = request.getParameter("productId");
        String manufacturer = request.getParameter("manufacturer");
        String item = request.getParameter("item");
        String description = request.getParameter("description");
        String price1 = request.getParameter("price");
        float price = Float.parseFloat(price1);
        String available1 = request.getParameter("available");
        boolean available = Boolean.parseBoolean(available1);
        
        //using servletContext to get dbConnection from deployment descriptor(web.xml)
        ServletContext sc = this.getServletContext();
        
        DBConnection dbConnection = (DBConnection) sc.getAttribute("dbConnection");
        Connection conn = dbConnection.getConnection();
        
        //use ProductDAO to add the product to the datebase
        ProductDAO productDAO = new ProductDAO();
        boolean result = productDAO.addProduct(conn, productId, manufacturer, item, description, price, available);
        
        RequestDispatcher rd = request.getRequestDispatcher("admin_main.jsp");
        rd.forward(request, response);
        
        if(result){
            System.out.println("Successfully added to the database");
        }
        else{
            System.out.println("Unsuccessful");
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

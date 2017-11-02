

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


public class EditProductInfoServlet extends HttpServlet {
   
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
        
        Product product = new Product();
        
        //Pulling the values of the variables from request object from the JSP
        
        String manufacturer = request.getParameter("manufacturer");
        String item = request.getParameter("item");
        String description = request.getParameter("description");
        String price1 = request.getParameter("price");
        double price = Double.parseDouble(price1);
        String available1 = request.getParameter("available");
        boolean available = Boolean.parseBoolean(available1);
        String productId = request.getParameter("productId");
        
        //Setting the values of the variables to the Product JavaBean

        product.setManufacturer(manufacturer);
        product.setDescription(description);
        product.setItem(item);
        product.setPrice(price);
        product.setAvailable(available);
        product.setProductId(productId);
        
        
        //Get the dbConnection from Servelet Context
        ServletContext sc = this.getServletContext();
        DBConnection dbConnection = (DBConnection)sc.getAttribute("dbConnection");
        
        //Establish a connection to the database
        Connection conn = dbConnection.getConnection();
        
        
        
        
        
        //Calling of the method to edit the products in the database
        ProductDAO productDAO = new ProductDAO();
        boolean result = productDAO.editProduct(conn, product);
        
        
        //Forward the request to admin_main.jsp
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

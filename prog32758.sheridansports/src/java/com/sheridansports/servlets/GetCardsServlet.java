
package com.sheridansports.servlets;

import com.prog32758.db.DBConnection;
import com.sheridansports.business.Card;
import com.sheridansports.dao.CardDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetCardsServlet extends HttpServlet {
       
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
        //Get the DBConnection from ServletContext
        ServletContext sc = this.getServletContext();
        DBConnection dbConnection = (DBConnection)sc.getAttribute("dbConnection");
        
        //Establish a connection to the database
        Connection conn= dbConnection.getConnection();
        
        // Get a list of all credit cards 
        CardDAO cardDAO = new CardDAO();
        ArrayList<Card> cards = cardDAO.getAllCards(conn);
        
        //Store the list of cc as a request attr so that the JSP we are forwarding to can access them
        request.setAttribute("cards", cards);
        
        //Forward the request to creditcardlist.jsp so that it can display the table of all cc
        RequestDispatcher rd= request.getRequestDispatcher("creditcardlist.jsp");
        rd.forward(request,response);
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


<%@page import="java.util.ArrayList"%>
<%@page import="com.sheridansports.business.Card"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    //Use JSP scriptlet to get the arraylist of product beans stored as a req attr
    ArrayList<Card> cards = (ArrayList<Card>)request.getAttribute("cards");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/sheridansports.css" />
        <title>Sheridan Sports Shop</title>
    </head>
    <body>
        <h1 class="pageheader">Cards List</h1>
        
        <table class="centertable" border="1">
            <tr>
                <th>Card ID</th>
                <th>User ID</th>
                <th>Card Number</th>
                <th>Card Type</th>
                <th>Expiry Month</th>
                <th>Expiry Year</th>
                <th>Card Holder</th>
            </tr>
            <%
                //Use a for-loop to add each product to the table. Only products that are available should be display
                for(int i=0; i< cards.size(); i++) {
                    Card current = cards.get(i);
                   
                    
                        // Add it to the table
            %>
            <!-- use HTML to display a new row in the table -->
            <tr align="middle">
                <td><%= current.getCardId() %></td>
                <td><%= current.getUserId() %></td>
                <td><%= current.getCardNumber() %></td>
                <td><%= current.getCardType() %></td>
                <td><%= current.getExpiryMonth() %></td>
                <td><%= current.getExpiryYear() %></td>
                <td><%= current.getCardHolder() %></td>
            </tr>
            
            <%        
                }
            %>
        </table>
    </body>
</html>
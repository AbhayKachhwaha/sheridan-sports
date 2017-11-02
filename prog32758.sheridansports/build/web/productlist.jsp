
<%@page import="java.util.ArrayList"%>
<%@page import="com.sheridansports.business.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<%
    //Use JSP scriptlet to get the arraylist of product beans stored as a req attr
    ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/sheridansports.css" />
        <title>Sheridan Sports Shop</title>
    </head>
    <body>
        <h1 class="pageheader">Product List</h1>
        <a href="index.html" style="float:left"><-Home</a>
        <a href="confirmpurchase.jsp" style="float: right">Checkout-></a>
        <table class="centertable">
            <tr>
                <th></th>
                <th>Item</th>
                <th>Description</th>
                <th>Price</th>
                <th>Add Item to Cart</th>
            </tr>
            <%
                //Use a for-loop to add each product to the table. Only products that are available should be displayed
                for(int i=0; i < products.size(); i++) {
                    Product current = products.get(i);
                    // Check if the current product is available
                    if(current.isAvailable()) {
                        // Add it to the table
            %>
            <!-- use HTML to display a new row in the table -->
            <tr>
                <td><input type="hidden" value="<%= current.getProductId() %>"/></td>
                <td><%= current.getManufacturer() %>
                <%= current.getItem() %></td>
                <td><%= current.getDescription() %></td>
                <td>$<%= current.getPrice() %></td>
                <td>
                    <form action="<c:url value="AddToCart.do">
                              <c:param name="productId" value="<%=current.getProductId()%>" />
                              <c:param name="price" value="<%= String.valueOf(current.getPrice())%>" />
                           </c:url>" method="POST">
                        <input type="submit" value="Add"/>
                        
                    </form>
                </td>
            </tr>
            
            <%        }
                }
            %>
        </table>
    </body>
</html>

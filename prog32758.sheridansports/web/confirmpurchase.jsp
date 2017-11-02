
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/sheridansports.css" />
        <title>Sheridan Sports Shop</title>
    </head>
    <body>
        <h1 class="pageheader">Confirm your purchase?</h1>
        <a href="index.html" style="float:left">Home</a>
        <!-- Use the set tag to keep track of the totalCost -->
        <c:set var="totalCost" value="0"/>
        
        <table style="margin-left: auto; margin-right: auto;">
            <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Subtotal</th>
                <th></th>
            </tr>
            
            
            <c:forEach var="item" items="${cart}">
            <!-- Var to hold the item subTotal -->
            <c:set var="itemSubtotal" value="${item.quantity * item.price}" />
            <tr>
                <td>${item.product.productId}</td>
                <td>${item.quantity}</td>
                <td>
                    $<fmt:formatNumber value="${item.price}" minFractionDigits="2" maxFractionDigits="2"/>
                </td>
                <td>
                    $<fmt:formatNumber value="${itemSubtotal}" minFractionDigits="1" maxFractionDigits="2"/>
                </td>
                <td>
                    <form action ="RemoveFromCart.do" method="Post">
                        <input type="hidden" name="productId" value="${item.product.productId}"/>
                        <input type="hidden" name="price" value="${item.price}"/>
                        <input type="submit" value="Remove"/>
                    </form>
                </td>
            </tr>
            <c:set var="totalCost" value="${totalCost + itemSubtotal}" />
            </c:forEach>
            <tr>
                <td colspan="4" style="text-align: right">
                    <b>Total: </b>$<fmt:formatNumber value="${totalCost}" minFractionDigits="2" maxFractionDigits="2"/><br>
                    <b>Tax: </b>$<fmt:formatNumber value="${totalCost * 0.13}" minFractionDigits="2" maxFractionDigits="2" /><br>
                    <b>Grand Total: </b>$<fmt:formatNumber value="${totalCost * 1.13}" minFractionDigits="2" maxFractionDigits="2" />
                    <input type="hidden" name="grandTotal" value="${totalCost * 1.13}"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <!-- Form to redirect the user back to product list -->
                    <form action="GetProducts.do" method="GET">
                        <input type="submit" value="Continue Shopping"/>
                    </form>
                </td>
                <td colspan="2" style="text-align: center">
                    <!-- Form to proceed with the checkout -->
                    <form action="ConfirmPurchase.do" method="POST">
                        <input type="hidden" name="grandTotal" value="${totalCost * 1.13}"/>
                        <input type="submit" value="Checkout"/>
                    </form>
                </td>
                
            </tr>
        </table>
        
    </body>
</html>

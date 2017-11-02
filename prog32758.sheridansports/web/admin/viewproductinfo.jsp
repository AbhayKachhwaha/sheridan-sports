
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sheridan Sports Shop - Admin Section</title>
        <link rel="stylesheet" type="text/css" href="../css/sheridansports.css" />
        
        
    </head>
    <body>
        <h1 class="pageheader">
            Product ID: 
            ${product.productId}
        </h1>
        
       
        <form action="EditProductInfo.do" method="POST">
            <table class="centertable">
                <!-- Adding form input fields inside this table... -->
                <tr>
                    <td class="label">Product ID:</td>
                    <td><input type="text" name="productId" value="${product.productId}" readonly/></td>
                    
                </tr>
                <tr>
                    <td class="label">Manufacturer:</td>
                    <td><input type="text" name="manufacturer" value="${product.manufacturer}"/></td>
                </tr>
                <tr>
                    <td class="label">Item Name: </td>
                    <td><input type="text" name="item" value="${product.item}"/></td>
                </tr>
                 <tr>
                    <td class="label">Description: </td>
                    <td><input type="text" name="description" value="${product.description}"/></td>
                </tr>
                 <tr>
                    <td class="label">Price: </td>
                    <td><input type="number" name="price" value="${product.price}" step=".01"/></td>
                </tr>
                 <tr>
                    <td class="label">Available: </td>
                    <td><select name="available" value="${product.available}">
                            <option value="true" <c:if test="${product.available} == 1" >selected</c:if>>Yes</option>
                            <option value="false" <c:if test="${product.available} == 0" >selected</c:if>>No</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="formsubmit">
                        <input type="submit" value="Edit Product" /> 
                    </td>
                </tr>
                
            </table>
        </form>
    </body>
</html>

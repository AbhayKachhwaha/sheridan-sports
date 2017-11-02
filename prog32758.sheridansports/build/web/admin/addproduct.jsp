
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../css/sheridansports.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AddProduct.do" method="POST">
            <table class="centertable">
                <tr>
                    <td>Enter Product ID:</td>
                    <td><input type="text" name="productId"/></td>
                </tr>
                <tr>
                    <td>Enter Manufacturer:</td>
                    <td><input type="text" name="manufacturer"/></td>
                </tr>
                <tr>
                    <td>Enter Item Name:</td>
                    <td><input type="text" name="item"/></td>
                </tr>
                <tr>
                    <td>Enter Item Description:</td>
                    <td><input type="text" name="description"/></td>
                </tr>
                <tr>
                    <td>Enter Price:</td>
                    <td><input type="number" name="price" step=".01"/></td>
                </tr>
                <tr>
                    <td>Available</td>
                    <td><input type="radio" name="available" value="true"/>Yes</td>
                    <td><input type="radio" name="available" value="false"/>No</td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>

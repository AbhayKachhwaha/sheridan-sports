
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/sheridansports.css" />
        <title>Checkout</title>
    </head>
    <body>
        <form action="PurchaseSuccessful.do" method="POST">
            <table class="centertable">
                <tr>
                    <td>Card Number:</td>
                    <td><input type="text" name="cardNumber"/></td>
                </tr>
                <tr>
                    <td>Card Type:</td>
                    <td><select name="cardType">
                        <option value="mc">Master Card</option>
                        <option value="visa">VISA</option>
                        <option value="amex">American Express</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Expiry Month:</td>
                    <td><input type="text" name="expiryMonth"/></td>
                </tr>
                <tr>
                    <td>Expiry Year:</td>
                    <td><input type="text" name="expiryYear"/></td>
                </tr>
                <tr>
                    <td>Card Holder Name:</td>
                    <td><input type="text" name="cardHolder"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Pay"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>

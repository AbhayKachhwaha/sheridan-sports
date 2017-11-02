
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sheridan Sports Shop - Admin Section</title>
        <link rel="stylesheet" type="text/css" href="../css/sheridansports.css" />
    </head>
    <body>
        <h1 class="pageheader">Sheridan Sports Shop Administration</h1>
        
        <h2 class="subheader">Option 1: View And Edit Product Info</h2>
        <a href="Logout.do">Logout</a>
        <form action="ViewProductInfo.do" method="GET">
            <table class="centertable">
                <tr>
                    <td class="label">Enter Product ID:</td>
                    <td><input type="text" name="productId" /></td>
                </tr>
                
                <tr>
                    <td class="formsubmit" colspan="2">
                        <input type="submit" value="Submit" />
                    </td>
                </tr>
            </table>
        </form>
        
        <h2 class="subheader"><a href="GetCards.do">Option 2: View All Credit Cards</a></h2>
        <h2 class="subheader"><a href="addproduct.jsp">Option 3: Add Products</a></h2>
    </body>
</html>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Output</title>
    </head>
    <body>
        <h1>Your payment of $ <fmt:formatNumber value="${grandTotal1}" minFractionDigits="2" maxFractionDigits="2"/> was successful.</h1>
        <a href="index.html">Home</a>
    </body>
</html>

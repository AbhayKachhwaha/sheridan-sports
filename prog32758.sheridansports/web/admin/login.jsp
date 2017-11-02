
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FORM-Based Authentication</title>
    </head>
    <body>
        <h1>FORM-Based Authentication</h1>
        <a href="../index.html" style="float:left"><-Home</a>
        <form action="j_security_check" method="POST">
            <table>
                <tr>
                    <td style="text-align: right">Username: </td>
                    <td><input type="text" name="j_username"/></td>
                </tr>
                <tr>
                    <td style="text-align: right">Password: </td>
                    <td><input type="password" name="j_password"/></td>
                </tr>
                <tr>
                    <td style="text-align: center" colspan="2">
                        <input type="submit" value="Login"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

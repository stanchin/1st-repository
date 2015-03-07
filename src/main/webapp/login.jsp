
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>
    <div id="monitor">
        <div id = "header">
            Login page
        </div>
        <div id = "nav">
            <div class = "link">
                <p><a href="index.jsp">Home</a></p>
                <p><a href ="login.jsp">Login</a></p>
            </div>
        </div>
    <div id = "section">
    <form id = "login" action="loginServlet" method="post">
        E-mail:<br>
        <input type="text" name="email" maxlength="30">
        <br>
        Password:<br>
        <input type="password" name="password" maxlength="30">
        <br><br>
        <input type="SUBMIT" name="action" value="login" class="myButton">
        <input type="SUBMIT" name="action" value="logout" class="myButton">
    </form>
    </div>
    </div>
    <div id = "footer">
        CreatedBy © Stanchin Denis
    </div>
    </body>
</html>

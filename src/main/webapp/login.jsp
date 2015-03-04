
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div id = "header">
    <h1>MOS Login</h1>
</div>
<div id = "nav">
    <div class = "link">
        <a href = "login.jsp">Login</a>
        <br>
        <a href="client.jsp">Profile</a>
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
    <input type="SUBMIT" value="Submit">
</form>
</div>
<div id = "footer">
    <p>
        CreatedBy © Stanchin Denis
    </p>
</div>
</body>
</html>

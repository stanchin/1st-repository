<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div id = "header">
    <h1>Profile page of ${client.name}</h1>
</div>
<div id = "nav">
    <div class = "link">
        <a href = "login.jsp">Login</a>
        <br>
        <a href="client.jsp">Profile</a>
    </div>
</div>
<div id = "section">
    <jsp:include page="navBar.jsp"/>
    <div>

    </div>
</div>
<div id = "footer">
    <p>
        CreatedBy © Stanchin Denis
    </p>
</div>
</body>
</html>

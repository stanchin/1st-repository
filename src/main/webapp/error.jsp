<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Mobile Operator</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div id="monitor">
    <div id = "header">
        Welcome to Mobile Operator System
    </div>
    <div id = "nav">
        <div class = "link">
            <p><a href="index.jsp">Home</a></p>
            <p><a href = "login.jsp">Login</a></p>
        </div>
    </div>
    <div id = "section">
        <div id="errorMsg">
            ${sessionScope.get("error")}
        </div>
    </div>
</div>
<div id = "footer">
    CreatedBy © Stanchin Denis
</div>
</body>
</html>

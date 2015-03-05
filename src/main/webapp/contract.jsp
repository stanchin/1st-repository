<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>
<jsp:useBean id="contracts" scope="session" type="com.tsystems.javaschool.entities.Contract"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client contracts</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div id = "header">
    <h1>Contracts of ${client.name}</h1>
</div>
<div id = "nav">
    <div class = "link">
        <a href = "login.jsp">Login</a>
        <br>
        <a href="client.jsp">Profile</a>
    </div>
</div>
<div id = "section">
    <div id="topNav">
        <ul>
            <li><input type="button" value="Contract" class="myButton"
                       onclick="document.getElementById('form1').submit()"></li>
            <li><input type="button" value="Tariffs" class="myButton"></li>
            <li><input type="button" value="Options" class="myButton"></li>
            <li><input type="button" value="Block" class="myButton"></li>
        </ul>
    </div>
    <section>
        <div id="content">

        </div>
    </section>
    <aside>
        <p>Name: ${client.name}</p>
        <p>Surname: ${client.surname}</p>
        <p>B-Day: ${client.birthday}</p>
        <p>Address: ${client.address}</p>
        <p>Email: ${client.email}</p>
    </aside>
</div>
<div id = "footer">
    <p>
        CreatedBy © Stanchin Denis
    </p>
</div>
<form id="form1" action="contractServlet" method="post">
    <input type="hidden" name="clientId" value=${client.id}>
    <input type="hidden" name="action" value="getContract">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
</body>
</html>

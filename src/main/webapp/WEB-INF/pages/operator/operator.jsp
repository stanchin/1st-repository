<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery-1.11.2.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/cover.css"/>">
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/> "></script>
</head>
<body>
This is a page of admin.
<%--<div id="monitor">
    <div id = "header">
        Profile page of ${client.name}
    </div>
    <div id = "nav">
        <div class = "link">
            <p><a href="../index.jsp">Home</a></p>
            <p><a href = "../login.jsp">Login</a></p>
            <p><a href="operator.jsp">Profile</a></p>
        </div>
    </div>
    <div id = "section">
        <div id="topNav">
            <ul>
                <li><input id="1" type="button" value="Add option" class="myButton"></li>
                <li><input id="2" type="button" value="Add tariff" class="myButton"></li>
                <li><input id="3" type="button" value="Add contract" class="myButton"></li>
                <li><input id="4" type="button" value="Add client" class="myButton"></li>
                <li><input id="7" type="button" value="Add role" class="myButton"></li>
                <li><input id="8" type="button" value="Add IncOption" class="myButton"></li>
                <li><input id="9" type="button" value="Add ReqOption" class="myButton"></li>
            </ul>
            <br>
            <ul>
                <li><input id="5" type="button" value="Show clients" class="myButton"></li>
                <li><input id="6" type="button" value="Show contracts" class="myButton"></li>
                <li><input id="10" type="button" value="Show options" class="myButton"></li>
            </ul>
        </div>
        <div id="content">

        </div>
        <aside>
            <p>Name: ${client.name}</p>
            <p>Surname: ${client.surname}</p>
            <p>B-Day: ${client.birthday}</p>
            <p>Address: ${client.address}</p>
            <p>Email: ${client.email}</p>
        </aside>
    </div>
</div>
<div id = "footer">
    CreatedBy © Stanchin Denis
</div>--%>
</body>
</html>

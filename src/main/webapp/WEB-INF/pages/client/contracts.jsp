<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="../../../css/style.css" type="text/css">
</head>
<body>
<div id="monitor">
    <div id = "header">
        Profile page of ${client.name}
    </div>
    <div id = "nav">
        <div class = "link">
            <p><a href="../index.jsp">Home</a></p>
            <p><a href = "../login.jsp">Login</a></p>
            <p><a href="client.jsp">Profile</a></p>
        </div>
    </div>
    <div id = "section">
        <div id="topNav">
            <ul>
                <li><input type="button" value="Contract" class="myButton"
                           onclick="document.getElementById('form1').submit()"></li>
                <li><input type="button" value="Tariffs" class="myButton"
                           onclick="document.getElementById('form2').submit()"></li>
                <li><input type="button" value="Options" class="myButton"></li>
                <li><input type="button" value="Block" class="myButton"></li>
            </ul>
        </div>
        <section>
            <div id="content">
                <c:forEach var="contract" items="contracts">
                    <p>${contract.getId()}</p>
                </c:forEach>
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
</div>
<div id = "footer">
    CreatedBy © Stanchin Denis
</div>
<form id="form1" action="auth/clientServlet" method="post">
    <input type="hidden" name="action" value="showContracts">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<form id="form2" action="auth/clientServlet" method="post">
    <input type="hidden" name="action" value="showTariffs">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<form id="form3" action="auth/clientServlet" method="post">
    <input type="hidden" name="action" value="showTariffOptions">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<form id="form4" action="auth/clientServlet" method="post">
    <input type="hidden" name="action" value="blockContract">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<form id="form5" action="auth/clientServlet" method="post">
    <input type="hidden" name="action" value="deployContract">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
</body>
</html>

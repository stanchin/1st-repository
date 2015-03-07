<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <script type="text/javascript" src="script/jquery-1.11.2.min.js"></script>
</head>
<body>
<div id="monitor">
    <div id = "header">
        Profile page of ${client.name}
    </div>
    <div id = "nav">
        <div class = "link">
            <p><a href="index.jsp">Home</a></p>
            <p><a href = "login.jsp">Login</a></p>
            <p><a href="admin.jsp">Profile</a></p>
        </div>
    </div>
    <div id = "section">
        <div id="topNav">
            <ul>
                <li><input id="1" type="button" value="Add option" class="myButton"></li>
                <li><input id="2" type="button" value="Add tariff" class="myButton"></li>
                <li><input id="3" type="button" value="Add contract" class="myButton"></li>
                <li><input id="4" type="button" value="Add client" class="myButton"></li>
                <li><input id="5" type="button" value="Show clients" class="myButton"></li>
                <li><input id="6" type="button" value="Show contracts" class="myButton"></li>
                <li><input id="7" type="button" value="Add role" class="myButton"></li>
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
</div>
<div id = "footer">
    CreatedBy © Stanchin Denis
</div>
<form id="form2" action="adminServlet" method="post">
    <input type="hidden" name="action" value="showTariffs">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<form id="form3" action="adminServlet" method="post">
    <input type="hidden" name="action" value="showTariffOptions">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<form id="form4" action="adminServlet" method="post">
    <input type="hidden" name="action" value="blockContract">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<form id="form5" action="adminServlet" method="post">
    <input type="hidden" name="action" value="deployContract">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<script>
    $(document).ready(function(){
        $('#1').click(function(){
            $('#content').load("addOptionForm.jsp");
        });
        $('#2').click(function(){
            $('content').load();
        });
        $('#3').click(function(){
            $('content').load();
        });
        $('#4').click(function(){
            $('#content').load("addClientForm.jsp");
        });
        $('#5').click(function(){
            $('content').load();
        });
        $('#6').click(function(){
            $('content').load();
        });
    });
</script>
</body>
</html>

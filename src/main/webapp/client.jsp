<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>

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
        Name: ${client.name}
        ${client.surname}<br>
        B-Day: ${client.birthday}
    </aside>
</div>
<div id = "footer">
    <p>
        CreatedBy © Stanchin Denis
    </p>
</div>
<form id="form1">
    <input type="hidden" name="clientId" value=${client.id}>
    <input type="hidden" name="action" value="getContract">
    <input type="hidden" name="sessionStatus" value=${sessionScope.get("session").isOpened()}>
</form>
<script type="text/javascript">
    $(document).ready(function(){

        $('#form1').submit(function(){
            $.ajax({
                type: "POST",
                url: "localhost:8080/mobile/contractServlet",
                success: function(jsp){
                    $("#content").load("/contract.jsp");
                }
            });
            return false;
        });
    });
</script>
</body>
</html>

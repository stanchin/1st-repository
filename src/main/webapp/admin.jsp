<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
</head>
<body>
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
            <li><input type="button" value="Contracts" class="myButton"
                       onclick="document.getElementById(/*TODO: feel*/).submit()"></li>
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

</body>
</html>

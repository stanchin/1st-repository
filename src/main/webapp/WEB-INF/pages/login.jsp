<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery-1.11.2.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css"/> "/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/cover.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/signin.css"/>">
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/> "></script>
</head>
<body onload='document.loginForm.email.focus();'>

<jsp:include page="parts/header.jsp"/>

<div class="inner-cover">
    <c:if test="${param.error == 'true'}">
        <p class="bg-danger" id="error">Invalid login or password</p>
    </c:if>

    <script>
        setInterval(function() {
            $("#error").attr("hidden", true);
        }, 4000);
    </script>

    <form name = "loginForm" class="form-signin" action="<c:url value='/j_spring_security_check' />" method="POST">

        <h2 class="form-signin-heading">Please sign in</h2>

        <label for="inputEmail" class="sr-only">Email address</label>
        <input name ="email" type="email" id="inputEmail"
               class="form-control" placeholder="Email address" required autofocus>
        <br/>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword"
               class="form-control" placeholder="Password" required>

        <button class="btn btn-lg btn-default" type="submit">Sign in</button>

    </form>

</div>

<jsp:include page="parts/footer.jsp"/>

</body>
</html>
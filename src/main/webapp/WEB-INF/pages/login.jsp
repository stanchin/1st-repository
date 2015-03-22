<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/signin.css"/> "/>
</head>

<jsp:include page="parts/header.jsp"></jsp:include>

<div class="site-wrapper">
    <div class="site-wrapper-inner">
        <div class="container">

            <form class="form-signin" action="login">
                <h2 class="form-signin-heading">Please sign in</h2>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-default" type="submit">Sign in</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="parts/footer.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery-1.11.2.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css"/> "/>
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/cover.css"/>">
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/> "></script>
    </head>
<body>

<jsp:include page="parts/header.jsp"/>

<div class="inner cover">
    <h1 class="cover-heading">Greetings, <sec:authentication property="principal.username"/> !</h1>
    <p class="lead">Now, you can go to your profile on the link below.</p>
    <p class="lead">
        <sec:authorize access="hasRole('client')">
            <a href="<c:url value="/goToClientPage"/> " class="btn btn-lg btn-default">Profile</a>
        </sec:authorize>
        <sec:authorize access="hasRole('admin')">
            <a href="<c:url value="/goToAdminPage"/> " class="btn btn-lg btn-default">Profile</a>
        </sec:authorize>
    </p>
</div>

<jsp:include page="parts/footer.jsp"/>

</body>
</html>

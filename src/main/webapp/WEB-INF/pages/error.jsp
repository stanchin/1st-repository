<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery-1.11.2.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css"/> "/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/cover.css"/>">
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
</head>
<body>

<jsp:include page="parts/header.jsp"/>

<div class="inner cover">
    <h1 class="cover-heading">Something wrong.</h1>
    <p class="lead"><spring:message text="${errorMsg}" javaScriptEscape="false"/></p>
</div>

<jsp:include page="parts/footer.jsp"/>

</body>
</html>

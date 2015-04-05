<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<jsp:include page="../parts/header.jsp"/>

<div class="inner cover">

    <h1 class="cover-heading">Incompatible options</h1>
    <br/>

    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Option price</th>
            <th>Connection price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="option" items="${incOptions}">
            <tr>
                <td>${option.name}</td>
                <td>${option.optionPrice}</td>
                <td>${option.connectionPrice}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="../parts/footer.jsp"/>

</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery-1.11.2.min.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.css"/> "/>
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/cover.css"/>">
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/> "></script>
    </head>
<body>

<jsp:include page="../parts/header.jsp"/>

<div class="inner cover">

    <h1 class="cover-heading">Clients</h1>
    <br/>

    <table class="table">
        <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Passport</th>
                <th>Conclude contract</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td><spring:message text="${client.name}" javaScriptEscape="false"/></td>
                <td><spring:message text="${client.surname}" javaScriptEscape="false"/></td>
                <td><spring:message text="${client.passport}" javaScriptEscape="false"/></td>
                <form id="${client.id}" action="<c:url value="/getAddContractPage"/>" method="get">
                    <td>
                        <button class="btn btn-sm btn-default btn-block"
                                onclick="document.getElementById('${client.id}').submit()">New contract</button>
                    </td>
                    <input type="hidden" name="name" value="${client.name}">
                    <input type="hidden" name="surname" value="${client.surname}">
                </form>
            </tr>
        </tbody>
        </c:forEach>
    </table>

</div>

<jsp:include page="../parts/footer.jsp"/>

</body>
</html>

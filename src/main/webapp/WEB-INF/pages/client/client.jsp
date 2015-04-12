<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>

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
        <h1 class="cover-heading">Information</h1>
        <br/>

        <c:if test="${not empty success}">
            <p class="bg-success" id="error"><spring:message text="${success}" javaScriptEscape="false"/></p>
        </c:if>

        <script>
            setInterval(function() {
                $("#error").attr("hidden", true);
            }, 4000);
        </script>

        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Address</th>
                    <th>Birthday</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><spring:message text="${client.name}" javaScriptEscape="false"/></td>
                    <td><spring:message text="${client.surname}" javaScriptEscape="false"/></td>
                    <td><spring:message text="${client.address}" javaScriptEscape="false"/></td>
                    <td><spring:message text="${client.birthday}" javaScriptEscape="false"/></td>
                </tr>
            </tbody>
        </table>

            <p class="lead">You can see your contracts on the link below.</p>
            <p class="lead">
                <a href="<c:url value="/getClientContracts"/> " class="btn btn-lg btn-default">Contracts</a>
            </p>
    </div>

    <jsp:include page="../parts/footer.jsp"/>
</body>
</html>

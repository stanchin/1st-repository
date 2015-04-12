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

    <h1 class="cover-heading">Information</h1><br/>

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

    <br/>

    <h2 class="cover-heading">Control Administrator Panel<br/>
        <small>It will redirect you to necessary pages.</small></h2>
    <br/>
    <p class="lead">Choose the operations and click buttons below.</p>
    <table class="table">
        <tbody>
        <tr>
            <td><a href="<c:url value="/getAddClientPage"/> "
                   class="btn btn-sm btn-default btn-block">Add client</a></td>
            <td><a href="<c:url value="/getAddTariffPage"/> "
                   class="btn btn-sm btn-default btn-block">Add tariff</a></td>
            <td><a href="<c:url value="/getClients"/> "
                   class="btn btn-sm btn-default btn-block">Show clients</a></td>
        </tr>
        <tr>
            <td><a href="<c:url value="/getAddOptionPage"/> "
                   class="btn btn-sm btn-default btn-block">Add option</a></td>
            <td><a href="<c:url value="/getAddReqOptionsPage"/> "
                   class="btn btn-sm btn-default btn-block">Add req options</a></td>
            <td><a href="<c:url value="/getAddIncOptionsPage"/> "
                   class="btn btn-sm btn-default btn-block">Add inc options</a></td>
        </tr>
        <tr>
            <td><a href="<c:url value="/getTariffs"/> "
                   class="btn btn-sm btn-default btn-block">Show tariffs</a></td>
            <td><a href="<c:url value="/getContracts"/> "
                   class="btn btn-sm btn-default btn-block">Show contracts</a></td>
            <td><a href="<c:url value="/getOptions"/> "
                   class="btn btn-sm btn-default btn-block">Show options</a></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form action="findClient" method="get">
                    <div class="form-group">
                        <input type="number" name="number"
                               class="form-control" placeholder="Enter number" maxlength="10" pattern="[0-9]{10}">
                    </div>
                    <button type="submit" class="btn btn-default">Find</button>
                </form>
            </td>
            <td></td>
        </tr>
        </tbody>
    </table>
</div>

<jsp:include page="../parts/footer.jsp"/>

</body>
</html>

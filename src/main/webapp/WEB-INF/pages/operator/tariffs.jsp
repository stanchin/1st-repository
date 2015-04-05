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
    <h1 class="cover-heading">Tariffs</h1>
    <br/>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Tariff price</th>
            <th>Options</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tariff" items="${tariffs}">
            <tr>
                <td>${tariff.name}</td>
                <td>${tariff.price}</td>
                <td>
                    <form id="1" action="<c:url value='/getTariffOptionsByAdmin'/>" method="get">

                        <button class="btn btn-sm btn-default btn-block"
                                onclick="document.getElementById('1').submit()">Options</button>
                        <input type="hidden" name="tariffId" value="${tariff.id}">
                    </form>
                </td>
                <td>
                    <form id="2" action="<c:url value='/dropTariff'/>" method="post">

                        <button class="btn btn-sm btn-default btn-block"
                                onclick="document.getElementById('2').submit()">Delete</button>
                        <input type="hidden" name="tariffId" value="${tariff.id}">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="../parts/footer.jsp"/>

</body>
</html>

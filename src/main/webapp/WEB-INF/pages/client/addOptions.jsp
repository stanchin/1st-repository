<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <h1 class="cover-heading">Options</h1>
    <br/>
    <p class="lead">Choose the options you want to add to your contract. Remember, that some
    options has required and incompatible ones.</p>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Option price</th>
            <th>Connection price</th>
            <th>Add</th>
        </tr>
        </thead>
        <tbody>
        <form:form id="1" action="addContractOption" method="post">
            <c:forEach var="option" items="${options}">
                <tr>
                    <td>${option.name}</td>
                    <td>${option.optionPrice}</td>
                    <td>${option.connectionPrice}</td>
                    <td><input type="checkbox" name="optionId" value="${option.id}"></td>
                </tr>
            </c:forEach>
            <input type="hidden" name="contractId" value="${contractId}"/>
        </form:form>
        <tr>
            <td><button class="btn btn-sm btn-default"
                       onclick="document.getElementById('1').submit()">Add options</button></td>
        </tr>
        </tbody>
    </table>
</div>

<jsp:include page="../parts/footer.jsp"/>
</body>
</html>
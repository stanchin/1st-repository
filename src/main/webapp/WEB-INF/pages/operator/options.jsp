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
    <h1 class="cover-heading">Options</h1>
    <br/>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Option price</th>
            <th>Connection price</th>
            <th>Required</th>
            <th>Incompatible</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="option" items="${options}">
                <tr>
                    <td><spring:message text="${option.name}" javaScriptEscape="false"/></td>
                    <td><spring:message text="${option.optionPrice}" javaScriptEscape="false"/></td>
                    <td><spring:message text="${option.connectionPrice}" javaScriptEscape="false"/></td>
                    <td>
                        <form id="1" action="<c:url value='/getRequiredOptions'/>" method="get">

                            <button class="btn btn-sm btn-default btn-block"
                                    onclick="document.getElementById('1').submit()">Show</button>
                            <input type="hidden" name="optionId" value="${option.id}">
                        </form>
                    </td>
                    <td>
                        <form id="2" action="<c:url value='/getIncompatibleOptions'/>" method="get">

                            <button class="btn btn-sm btn-default btn-block"
                                    onclick="document.getElementById('2').submit()">Show</button>
                            <input type="hidden" name="optionId" value="${option.id}">
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

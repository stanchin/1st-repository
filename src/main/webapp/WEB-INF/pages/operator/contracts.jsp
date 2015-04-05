<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

    <h1 class="cover-heading">Contracts</h1>
    <br/>

    <c:if test="${not empty success}">
        <p class="bg-success" id="error">${success}</p>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th>Number</th>
            <th>Tariff</th>
            <th>Change tariff</th>
            <th>Extra options</th>
            <th>Add on options</th>
            <th>Lock/Unlock</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contracts}">
        <tr>
            <td>${contract.number.number}</td>
            <td>${contract.tariff.name}</td>
            <td>
                <form id="change${contract.id}"
                      action="<c:url value="/getChangeContractTariffFormByAdmin"/>" method="get">
                    <button class="btn btn-sm btn-default btn-block"
                            onclick="document.getElementById('change${contract.id}').submit()">Change</button>
                    <input type="hidden" name="contractId" value="${contract.id}">
                </form>
            </td>
            <td>
                <form id="${contract.id}opt" action="<c:url value="/getContractOptionsPage"/>" method="get">
                    <button class="btn btn-sm btn-default btn-block"
                            onclick="document.getElementById('${contract.id}opt').submit()">Show</button>
                    <input type="hidden" name="contractId" value="${contract.id}">
                </form>
            </td>
            <td>
                <form id="${contract.id}" action="<c:url value="/getAddExtraOptionsPage"/>" method="get">
                    <button class="btn btn-sm btn-default btn-block"
                            onclick="document.getElementById('${contract.id}').submit()">New options</button>
                    <input type="hidden" name="contractId" value="${contract.id}">
                    <input type="hidden" name="number" value="${contract.number.number}">
                    <input type="hidden" name="tariffId" value="${contract.tariff.id}">
                </form>
            </td>
            <td>
                <c:if test="${contract.blockedByOperator == false}">
                    <form id="lock${contract.id}" action="<c:url value="/lockContract"/>" method="post">
                        <button class="btn btn-sm btn-default btn-block"
                                onclick="document.getElementById('lock${contract.id}').submit()">Lock</button>
                        <input type="hidden" name="number" value="${contract.number.number}">
                    </form>
                </c:if>
                <c:if test="${contract.blockedByOperator == true}">
                    <form id="unlock${contract.id}" action="<c:url value="/unlockContract"/>" method="post">
                        <button class="btn btn-sm btn-default btn-block"
                                onclick="document.getElementById('unlock${contract.id}bl').submit()">Unlock</button>
                        <input type="hidden" name="number" value="${contract.number.number}">
                    </form>
                </c:if>
            </td>
        </tr>
        </tbody>
        </c:forEach>
    </table>

</div>

<jsp:include page="../parts/footer.jsp"/>

</body>
</html>

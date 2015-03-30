<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <h1 class="cover-heading">Contracts</h1>
        <br/>
        <p class="lead">Here, you can add options or change tariff in any of your numbers.</p>
        <br/>
            <table class="table">
                <thead>
                <tr>
                    <th>Number</th>
                    <th>Tariff</th>
                    <th>Options</th>
                    <th>Add new options</th>
                    <th>Change tariff</th>
                </tr>
                </thead>
                <c:forEach var="contract" items="${requestScope.get('contracts')}">
                    <tbody>
                    <tr>
                        <td>${contract.number.getNumber()}</td>
                        <td>${contract.tariff.getName()}</td>
                        <td>
                            <form:form id="1" action="getTariffOptions" method="get">

                                <button class="btn btn-sm btn-default"
                                         onclick="document.getElementById('1').submit()">Options</button>

                                <input type="hidden" name="tariffId" value="${contract.tariff.getId()}"/>
                            </form:form>
                        </td>
                        <td>
                            <form:form id="2" action="getAddOptionsForm" method="get">

                                <button class="btn btn-sm btn-default"
                                        onclick="document.getElementById('2').submit()">Add options</button>

                                <input type="hidden" name="contractId" value="${contract.getId()}"/>
                            </form:form>
                        </td>
                        <td>
                            <form:form id="3" action="getChangeContractTariffForm" method="get">

                                <button class="btn btn-sm btn-default"
                                        onclick="document.getElementById('2').submit()">Change tariff</button>

                                <input type="hidden" name="contractId" value="${contract.getId()}"/>
                            </form:form>
                        </td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
    </div>

    <jsp:include page="../parts/footer.jsp"/>
</body>
</html>

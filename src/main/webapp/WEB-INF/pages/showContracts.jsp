<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="script/jquery-1.11.2.min.js"></script>
<table id="added">
    <tr>
        <td>Number</td>
        <td>Client</td>
        <td>Tariff</td>
    </tr>
    <c:forEach var="contract" items="${sessionScope.get('contracts')}">
        <tr>
            <td>${contract.getNumber().getNumber()}</td>
            <td>${contract.getClient().getName()}</td>
            <td>${contract.getTariff().getName()}</td>
        </tr>
    </c:forEach>
</table>
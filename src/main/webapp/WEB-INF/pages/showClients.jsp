<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="script/jquery-1.11.2.min.js"></script>
<table id="added">
    <tr>
        <td>Name</td>
        <td>Surname</td>
        <td>Email</td>
        <td>Passport</td>
    </tr>
    <c:forEach var="client" items="${sessionScope.get('clients')}">
        <tr>
            <td>${client.getName()}</td>
            <td>${client.getSurname()}</td>
            <td>${client.getEmail()}</td>
            <td>${client.getPassport()}</td>
        </tr>
    </c:forEach>
</table>

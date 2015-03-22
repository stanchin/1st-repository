<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="../../script/jquery-1.11.2.min.js"></script>
<table id="added">
    <tr>
        <td>Name</td>
        <td>Option price</td>
        <td>Connection price</td>
    </tr>
    <c:forEach var="option" items="${sessionScope.get('options')}">
        <tr>
            <td>${option.getName()}</td>
            <td>${option.getOptionPrice()}</td>
            <td>${option.getConnectionPrice()}</td>
        </tr>
    </c:forEach>
</table>

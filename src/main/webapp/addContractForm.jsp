<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="script/jquery-1.11.2.min.js"></script>
<form id="contract" action="adminServlet" method="post">
    <table>
        <tr>
            <td>Client name:</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td>Client surname:</td>
            <td>
                <input type="text" name="surname">
            </td>
        </tr>
        <tr>
            <td>Number:</td>
            <td id="num"></td>
        </tr>
        <tr>
            <td>Tariff:</td>
            <td>
                <select form="contract" required="required" name="tariffId"
                        size="${sessionScope.get('tariffs').size()}}">
                    <c:forEach var="tariff" items="${sessionScope.get('tariffs')}">
                        <option value="${tariff.getId()}">${tariff.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="submit" value="Add contract" class="myButton">
    <input type="hidden" name="action" value="addContract">
</form>
<button id="generate" class="myButton">Get unique</button>
<script>

    $(document).ready(function(){
        $('#generate').click(function(){
            var x = $.ajax('adminServlet', {
                type: 'post',
                data: {'action':'generateNumber'}
            });
            x.complete(function(){
                document.getElementById('num').innerHTML = x.response.getAttribute("number");
            })
        });
    });
</script>

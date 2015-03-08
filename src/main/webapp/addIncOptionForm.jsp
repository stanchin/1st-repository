<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id = "login" action="adminServlet" method="post">
    <table>
        <tr>
            <td>Base option:</td>
            <td>
                <select id="1" required>
                    <c:forEach var="option" items="${sessionScope.get('options')}">
                        <option value="${option.getId()}">${option.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Incompatible option:</td>
            <td>
                <select id="2" required>
                    <c:forEach var="option" items="${sessionScope.get('options')}">
                        <option value="${option.getId()}">${option.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="SUBMIT" value="Set" class="myButton">
    <input type="hidden" id="bo" name="baseOptionId">
    <input type="hidden" id="io" name="incOptionId">
    <input type="hidden" name="action" value="setIncompatibleOptions">
</form>
<script>
    $('#select').selected(function(){
        var x = $('#1').find('option').val();
        document.getElementById('bo').setAttribute('value', x);
        var y = $('#2').find('option').val();
        document.getElementById('bo').setAttribute('value', y);
    })
</script>


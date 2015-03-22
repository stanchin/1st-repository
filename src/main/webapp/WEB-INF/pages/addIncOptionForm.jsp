<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="../../script/jquery-1.11.2.min.js"></script>
<form id = "added" action="adminServlet" method="post">
    <table>
        <tr>
            <td>Base option:</td>
            <td>
                <select required="" name="baseOptionId" form="added">
                    <c:forEach var="option" items="${sessionScope.get('options')}">
                        <option value="${option.getId()}">${option.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Incompatible option:</td>
            <td>
                <select required="" name="incOptionsId" form="added" size="${sessionScope.get('options').size()}"
                        multiple="multiple">
                    <c:forEach var="option" items="${sessionScope.get('options')}">
                        <option value="${option.getId()}">${option.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input type="SUBMIT" value="Set" class="myButton">
    <input type="hidden" name="action" value="setIncompatibleOptions">
</form>



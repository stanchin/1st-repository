<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="script/jquery-1.11.2.min.js"></script>
<form id = "added" action="adminServlet" method="get">
    Tariff name:<br>
    <input type="text" name="name" maxlength="30">
    <br>
    <table>
    <c:forEach var="option" items="${sessionScope.get('options')}">
        <tr id="check${option.getId()}">
            <td><c:out value="${option.getName()}"/></td>
            <td><c:out value="${option.getOptionPrice()}"/></td>
            <td><c:out value="${option.getConnectionPrice()}"/></td>
            <td><input name="optionsId" id="box${option.getId()}" type="checkbox" value="${option.getId()}"></td>
        </tr>
        <script language="javascript">
            $(function(){
                $(":input:checkbox").click(function(){
                    if($("#box${option.getId()}").is(":checked")) {
                        <c:if test="${option.getReqOptions().size() != 0}">
                            <c:forEach items="${option.getReqOptions()}" var="reqOption">
                                $("#box${reqOption.getId()}").prop('checked', true);
                            </c:forEach>
                        </c:if>
                        <c:if test="${option.getIncOptions().size() != 0}">
                            <c:forEach items="${option.getIncOptions()}" var="incOption">
                                $("#check${incOption.getId()}").hide();
                            </c:forEach>
                        </c:if>
                    } else {
                        <c:if test="${option.getReqOptions().size() != 0}">
                            <c:forEach items="${option.getReqOptions()}" var="reqOption">
                                $("#box${reqOption.getId()}").prop('checked', false);
                            </c:forEach>
                        </c:if>
                        <c:if test="${option.getIncOptions().size() != 0}">
                            <c:forEach items="${option.getIncOptions()}" var="incOption">
                                $("#check${incOption.getId()}").show();
                            </c:forEach>
                        </c:if>
                    }
                });
            });
        </script>
    </c:forEach>
    </table>
    <input type="SUBMIT" class="myButton">
    <input type="hidden" name="action" value="addTariff">
</form>

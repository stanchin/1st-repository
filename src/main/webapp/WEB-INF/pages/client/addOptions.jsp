<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <p class="lead">Choose the options you want to add to your contract. Remember, that some
    options has required and incompatible ones.</p>

    <form action="addContractOptions" method="post">
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Option price</th>
            <th>Connection price</th>
            <th>Add</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="option" items="${options}">
            <tr id="check${option.id}">
                <td><spring:message text="${option.name}" javaScriptEscape="false"/></td>
                <td><spring:message text="${option.optionPrice}" javaScriptEscape="false"/></td>
                <td><spring:message text="${option.connectionPrice}" javaScriptEscape="false"/></td>
                <td><input type="checkbox" id="box${option.id}" name="optionsId" value="${option.id}"></td>
            </tr>
            <script>
                /*
                 * This function allows to validate required and incompatible options when operator click
                 * the input checkbox.
                 **/
                $(function(){
                    $("#box${option.id}").click(function(){
                        if($("#box${option.id}").is(":checked")) {
                            <c:if test="${option.reqOptions.size() != 0}">
                            <c:forEach items="${option.reqOptions}" var="reqOption">
                            $("#box${reqOption.id}").prop('checked', true);
                            </c:forEach>
                            </c:if>
                            <c:if test="${option.incOptions.size() != 0}">
                            <c:forEach items="${option.incOptions}" var="incOption">
                            $("#box${incOption.id}").prop('checked', false);
                            $("#check${incOption.id}").hide();
                            </c:forEach>
                            </c:if>
                        } else {
                            <c:if test="${option.reqOptions.size() != 0}">
                            <c:forEach items="${option.reqOptions}" var="reqOption">
                            $("#box${reqOption.id}").prop('checked', false);
                            </c:forEach>
                            </c:if>
                            <c:if test="${option.incOptions.size() != 0}">
                            <c:forEach items="${option.incOptions}" var="incOption">
                            $("#check${incOption.id}").show();
                            </c:forEach>
                            </c:if>
                        }
                    });
                });
            </script>
        </c:forEach>
        <tr>
            <td colspan="4">
                <button class="btn btn-sm btn-default btn-block" type="submit">Add options</button>
                <input type="hidden" name="contractId" value="${contractId}">
            </td>
        </tr>
        </tbody>
    </table>
    </form>
</div>

<jsp:include page="../parts/footer.jsp"/>
</body>
</html>
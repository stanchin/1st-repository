<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <h1 class="cover-heading">Create tariff</h1>
    <br/>
    <form action="<c:url value="/addTariff"/>" method="post">
        <table class="table">
            <tbody>
            <tr>
                <td>
                    <div class="form-group">
                        <label for="1">Tariff name</label>
                        <input type="text" name="name" class="form-control" id="1" placeholder="Enter name" required
                                pattern="^[a-zA-Z]+$" maxlength="30">
                    </div>
                </td>
            </tr>
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
            </tbody>
        </table>
        <button class="btn btn-lg btn-default" type="submit">Create</button>
    </form>
</div>

<jsp:include page="../parts/footer.jsp"/>

</body>
</html>

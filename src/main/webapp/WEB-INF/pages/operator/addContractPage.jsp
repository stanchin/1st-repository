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
    <h1 class="cover-heading">Conclude new contract<br/>
        <small>Please, choose the number and tariff.</small></h1>
    <br/>
    <form id="1" action="<c:url value="/concludeContract"/>" method="post">
        <table class="table">
            <tbody>
            <tr>
                <td>
                    <spring:message text="${name}" javaScriptEscape="false"/>
                    <input type="hidden" name="name" value="${name}">
                </td>
            </tr>
            <tr>
                <td>
                    <spring:message text="${surname}" javaScriptEscape="false"/>
                    <input type="hidden" name="surname" value="${surname}">
                </td>
            </tr>
            <tr>
                <td>
                    <select class="form-control" form="1" required="required" name="tariffId"
                            size="${tariffs.size()}">
                        <c:forEach var="tariff" items="${tariffs}">
                            <option value="${tariff.id}">
                                <spring:message text="${tariff.name}" javaScriptEscape="false"/>
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td id="number"></td>
            </tr>

            </tbody>
        </table>
        <button class="btn btn-lg btn-default" type="submit">Create</button>
        <input type="hidden" id="telNumber" name="number">
    </form>
    <button class="btn btn-sm btn-default" id="generate">Get unique</button>
</div>
<script>

    $(document).ready(function(){
        $('#generate').click(function(){
            $.ajax('generateNumber', {
                type: 'post',
                success: function(data){
                    document.getElementById('number').innerHTML = data;
                    $('#telNumber').val(data);
                }
            });
        });
    });
</script>

<jsp:include page="../parts/footer.jsp"/>
</body>
</html>

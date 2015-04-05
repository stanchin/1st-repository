<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/jquery-1.11.2.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/> "/>
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/cover.css"/>">
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/> "></script>
</head>
<body>
<jsp:include page="../parts/header.jsp"/>

<div class="inner cover">
    <h1 class="cover-heading">Change ${contract.number.number}'s tariff</h1>
    <br/>

    <form id="tariffChange" action="<c:url value="/changeTariff"/>" method="post">
        <div class="form-group">
            <label for="t" class="col-sm-2 control-label">Available tariffs</label>
            <div class="col-sm-10">

                <select required name="tariffId" form="tariffChange" class="form-control" id="t">
                    <c:forEach var="tariff" items="${tariffs}">
                        <option value="${tariff.id}">Name: ${tariff.name} Price: ${tariff.price}</option>
                    </c:forEach>
                </select>
            </div>
            <br/>
            <br/>
            <button class="btn btn-lg btn-default" type="submit">Change</button>
            <input type="hidden" name="contractId" value="${contract.id}">
        </div>
    </form>

</div>

<jsp:include page="../parts/footer.jsp"/>
</body>
</html>

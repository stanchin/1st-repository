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

    <h1 class="cover-heading">Add on incompatible options</h1>
    <br/>

    <form class="form-horizontal" action="<c:url value="/setIncOpt"/> " method="post" id="1">
        <div class="form-group">
            <label for="bo" class="col-sm-2 control-label">Base option</label>
            <div class="col-sm-10">

                <select required name="baseOptionId" form="1" class="form-control" id="bo">
                    <c:forEach var="option" items="${options}">
                        <option value="${option.id}">
                            <spring:message text="${option.name}" javaScriptEscape="false"/>
                        </option>
                    </c:forEach>

                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="io" class="col-sm-2 control-label">Incompatible options</label>
            <div class="col-sm-10">

                <select required name="incOptionsId" form="1" multiple="multiple" class="form-control" id="io">
                    <c:forEach var="option" items="${options}">
                        <option value="${option.id}">
                            <spring:message text="${option.name}" javaScriptEscape="false"/>
                        </option>
                    </c:forEach>

                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-sm btn-default btn-block">Set</button>
            </div>
        </div>
    </form>

</div>

<jsp:include page="../parts/footer.jsp"/>

</body>
</html>

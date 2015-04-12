<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <h1 class="cover-heading">Add option<br/>
        <small>Please, write the information about option.</small></h1>
    <br/>
    <form action="<c:url value="/addOption"/>" method="post">
        <table class="table">
            <tbody>
            <tr>
                <td><div class="form-group">
                    <label for="1">Option name</label>
                    <input type="text" name="name" class="form-control" id="1"
                           placeholder="Enter name" required pattern="^[a-zA-Z]+$" maxlength="30">
                </div>
                </td>
            </tr>
            <tr>
                <td><div class="form-group">
                    <label for="2">Option price</label>
                    <input type="text" name="optionPrice" class="form-control" id="2" placeholder="Example: 1005.00"
                           pattern="\d+(\.\d{2})?" required>
                </div>
                </td>
            </tr>
            <tr>
                <td><div class="form-group">
                    <label for="3">Connection price</label>
                    <input type="text" name="connectionPrice" class="form-control" id="3" placeholder="Example: 1005.00"
                           pattern="\d+(\.\d{2})?" required>
                </div>
                </td>
            </tr>

            </tbody>
        </table>
        <button class="btn btn-lg btn-default" type="submit">Create</button>
    </form>
</div>

<jsp:include page="../parts/footer.jsp"/>
</body>
</html>

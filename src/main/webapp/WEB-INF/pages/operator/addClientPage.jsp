<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>

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
    <h1 class="cover-heading">Add client</h1>
    <br/>
    <form action="<c:url value="/addClient"/>" method="post">
        <table class="table">
            <tbody>
                <tr>
                    <td>Name</td>
                    <td>
                        <div class="form-group">
                        <input type="text" name="name" class="form-control" id="1" placeholder="Enter name">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Surname</td>
                    <td>
                        <div class="form-group">
                        <input type="text" name="surname" class="form-control" id="2" placeholder="Enter surname">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td>
                        <div class="form-group">
                        <input type="date" name="birthday" class="form-control" id="3">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Passport</td>
                    <td>
                        <div class="form-group">
                        <input type="text" name="passport" class="form-control" id="4"
                               placeholder="Enter serial and number without whitespaces" maxlength="10">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>
                        <div class="form-group">
                        <input type="text" name="address" class="form-control" id="5" placeholder="Enter address">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>
                        <div class="form-group">
                        <input type="email" name="email" class="form-control" id="6" placeholder="Enter email">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>
                        <div class="form-group">
                        <input type="text" name="password" class="form-control" id="7" placeholder="Enter password">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div class="radio">
                            <label>
                                <input type="radio" name="roleId" value="1">
                                Admin
                            </label>
                            <label>
                                <input type="radio" name="roleId" value="2">
                                Client
                            </label>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <button class="btn btn-lg btn-default" type="submit">Register</button>
    </form>
</div>

<jsp:include page="../parts/footer.jsp"/>
</body>
</html>

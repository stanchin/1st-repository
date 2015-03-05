<jsp:useBean id="client" scope="session" type="com.tsystems.javaschool.entities.Client"/>
<jsp:useBean id="contract" scope="session" type="com.tsystems.javaschool.entities.Contract"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
        <c:forEach var="clientContract" items="${contract}">
            Contract number: ${contract.number}<br>
            Contract tariff: ${contract.tariff}
        </c:forEach>
</div>

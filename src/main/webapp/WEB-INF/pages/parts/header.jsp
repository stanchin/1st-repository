<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="site-wrapper">
    <div class="site-wrapper-inner">
        <div class="cover-container">
            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand">eCare-Spring</h3>
                    <nav>
                        <ul class="nav masthead-nav">
                            <li class="active"><a href="<c:url value="/home"/> ">Home</a></li>

                            <sec:authorize access="hasRole('anonymous')">

                                <li><a href="<c:url value="/goLogin"/>">Login</a></li>

                            </sec:authorize>

                            <sec:authorize access="hasRole('client')">

                                <li><a href="<c:url value="/goToClientPage"/>">
                                    <sec:authentication property="principal.username"/></a></li>

                                <li><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></li>

                            </sec:authorize>

                            <sec:authorize access="hasRole('admin')">

                                <li><a href="<c:url value="/goToAdminPage"/>">
                                    <sec:authentication property="principal.username"/></a></li>

                                <li><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></li>

                            </sec:authorize>

                        </ul>
                    </nav>
                </div>
            </div>

<%@include file="/layout/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<core:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>


<html>
<head>
    <title><fmt:message key="LOGIN"/></title>
    <link href="<c:url value="../css/fist.css"/>" rel="stylesheet">
</head>
<body>





<form action="${pageContext.request.contextPath}/handler" id="login-form" method="post">
    <div class="login">
        <label for="login-field"></label>
        <input type="email" name="email" id="login-field" placeholder=<fmt:message key="LOGIN"/>>
        <br>
        <label for="password-field"></label>
        <input type="password" name="password" id="password-field" placeholder=<fmt:message key="PASSWORD"/>>
        <br>
        <input type="hidden" name="Confirm" value="login"/>
        <input type="submit" class="loginSubmit" value=<fmt:message key="ENTER"/>>
    </div>
</form>
<div class="link">
    <a href="<c:url value="user/registration.jsp"/>" id="regLink" data-hover="<fmt:message key="REGISTRATION"/>">
        <span><fmt:message key="REGISTRATION"/></span>
    </a>
</div>
</body>
</html>

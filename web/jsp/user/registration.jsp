<%@include file="/layout/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<core:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>

<html>
<head>
    <title><fmt:message key="REGISTRATION"/></title>
    <link href="<c:url value="../css/registration.css"/>" rel="stylesheet">
</head>
<body>


<span>HI!</span>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<core:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>

<fmt:setLocale value="${setLocale}"/>
<fmt:setBundle basename="language"/>




<html>
<head>
    <title></title>
</head>
<body>

</body>
</html>

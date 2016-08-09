<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>


<fmt:setLocale value="${sessionScope.setLocale}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title></title>
    <link href="<c:url value="/css/header.css"/>" rel="stylesheet">

</head>
<body>

<div class="newLanguage"  >
    <form action="${pageContext.request.contextPath}/payments" method="get">
        <input type="hidden" name="language" value="ru_RU"/>
        <input type="hidden" name="Confirm" value="change_language"/>
        <input type="submit" value= <fmt:message key="RUS"/>>
    </form>


    <form action="${pageContext.request.contextPath}/payments" method="get">
        <input type="hidden" name="language" value="en_US"/>
        <input type="hidden" name="Confirm" value="change_language"/>
        <input type="submit"  value=<fmt:message key="ENG"/>>
    </form>
</div>

</body>
</html>
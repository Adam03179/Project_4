<%@include file="/jsp/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account lock</title>
    <link href="<c:url value="/css/mainpage.css"/>" rel="stylesheet">

</head>
<body>
<br>

<div class="mainPage">
    <form action="${pageContext.request.contextPath}/payments" method="post">

        <label for="select-account"><fmt:message key="SELECT_ACCOUNT"/></label>
        <select id="select-account" name="unlock" required>
            <option disabled selected><fmt:message key="ACCOUNTS"/></option>
            <c:forEach items="${sessionScope.lockedList}" var="lockedElem">
                <option>${lockedElem.number } ${lockedElem.balance} <fmt:message key="UAH"/></option>
            </c:forEach>
        </select>

        <input type="hidden" name="Confirm" value="Account_unlock"/>
        <input type="submit" value=<fmt:message key="UNLOCK"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="log_out"/>
        <input type="submit" value=<fmt:message key="LOG_OUT"/>>
    </form>


</div>
</body>
</html>

<%@include file="/jsp/user/mainPage.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account lock</title>
</head>
<body>
<br>

<div class="mainPage">
    <form action="${pageContext.request.contextPath}/payments" method="post">

        <label for="select-account"><fmt:message key="SELECT_ACCOUNT"/></label>
        <select id="select-account" name="lock" required>
            <option disabled selected><fmt:message key="ACCOUNTS"/></option>
            <c:forEach items="${sessionScope.accountsList}" var="historyElem">
                <c:set var="elem" value="${historyElem.blocked}"/>
                <c:set var="val" value="false"/>
                <c:if test="${elem eq val}">
                    <option>${historyElem.number }
                        <numformat:formatNumber format="#,##0.00" number="${historyElem.balance}"/>
                        <fmt:message key="UAH"/></option>
                </c:if>
            </c:forEach>
        </select>

        <input type="hidden" name="Confirm" value="Account_lock"/>
        <input type="submit" value=<fmt:message key="LOCK"/>>

    </form>

</div>
</body>
</html>

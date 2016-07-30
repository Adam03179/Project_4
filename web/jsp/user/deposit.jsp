<%@include file="/jsp/user/operations.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deposit</title>
</head>
<body>
<br>

<div class="mainPage">
    <form action="${pageContext.request.contextPath}/payments" method="post">

        <label for="select-account"><fmt:message key="SELECT_ACCOUNT"/></label>
        <select id="select-account" name="deposit" required>
            <option disabled selected><fmt:message key="ACCOUNTS"/></option>
            <c:forEach items="${sessionScope.accountsList}" var="historyElem">
                <c:set var="elem" value="${historyElem.blocked}"/>
                <c:set var="val" value="false"/>
                <c:if test="${elem eq val}">
                    <option>${historyElem.number}</option>
                </c:if>
            </c:forEach>
        </select>

        <label for="sum"><fmt:message key="SUM"/></label>
        <input type="number" step="0.01" min="0" id="sum" name="sum" required placeholder=<fmt:message key="UAH"/>>

        <input type="hidden" name="Confirm" value="deposit_operation"/>
        <input type="submit" value=<fmt:message key="CONFIRM"/>>

    </form>

</div>
</body>
</html>

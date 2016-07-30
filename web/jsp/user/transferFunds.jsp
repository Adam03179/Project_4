<%@include file="/jsp/user/operations.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer Funds</title>

</head>
<body>
<br>

<div class="mainPage">
    <form action="${pageContext.request.contextPath}/payments" method="post">

        <label for="select-account"><fmt:message key="SELECT_ACCOUNT"/></label>
        <select id="select-account" name="transfer" required>
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

        <label for="contrAcc"><fmt:message key="ACCOUNT"/></label>
        <input type="text" id="contrAcc" name="partnerAccount" required placeholder=<fmt:message key="ACCOUNT"/>>

        <label for="sum"><fmt:message key="SUM"/></label>
        <input type="number" id="sum" step="0.01" min="0" name="sum" required placeholder=<fmt:message key="UAH"/>>

        <input type="hidden" name="Confirm" value="transfer_operation"/>
        <input type="submit" value=<fmt:message key="CONFIRM"/>>

    </form>

</div>
</body>
</html>

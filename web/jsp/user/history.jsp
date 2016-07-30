<%@include file="/jsp/user/operations.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>History</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/payments" method="post">
    <table class="mainPage">
        <tr>
            <th><fmt:message key="NUMBER_OF_ACCOUNT"/></th>
            <th><fmt:message key="SUM"/></th>
            <th><fmt:message key="PARTNERS_NAME"/></th>
            <th><fmt:message key="OPERATION_DATE"/></th>
            <th><fmt:message key="OPERATION_TYPE"/></th>

        </tr>
        <br/>
        <c:forEach items="${sessionScope.historyList}" var="historyElem">
            <tr>
                <td>
                        ${historyElem.account.number}
                </td>
                <td>
                    <numformat:formatNumber format="#,##0.00" number="${historyElem.sum}"/>
                </td>
                <td>
                        ${historyElem.partnerName}
                </td>
                <td>
                        ${historyElem.operationDate}
                </td>
                <td>
                    <fmt:message key="${historyElem.operationType}"/>
                </td>

            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>

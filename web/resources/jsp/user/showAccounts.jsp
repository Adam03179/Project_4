<%@include file="/resources/jsp/user/mainPage.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Show accounts</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/handler" method="get">
    <table class="mainPage">
        <tr>
            <th><fmt:message key="NUMBER_OF_ACCOUNT"/></th>
            <th><fmt:message key="INTEREST"/></th>
            <th><fmt:message key="OPEN_DATE"/></th>
            <th><fmt:message key="BALANCE"/></th>
            <th><fmt:message key="CURRENCY"/></th>
            <th><fmt:message key="IS_BLOCKED"/></th>

        </tr>
        <br/>
        <c:forEach items="${sessionScope.accountsList}" var="accountElem">
            <tr>
                <td>
                        ${accountElem.number}
                </td>
                <td>
                        ${accountElem.interest}
                </td>
                <td>
                        ${accountElem.openDate}
                </td>
                <td>
                        ${accountElem.balance}
                </td>
                <td>
                    <fmt:message key="UAH"/>
                </td>
                <td>

                    <c:set var="elem" value="${accountElem.blocked}"/>
                    <c:set var="val" value="false"/>

                    <c:if test="${elem eq val}">
                        <fmt:message key="NOT_BLOCKED"/>
                    </c:if>
                    <c:if test="!${elem eq val}">
                        <fmt:message key="BLOCKED"/>
                    </c:if>
                </td>

            </tr>
        </c:forEach>
    </table>
</form>

</body>
</html>

<%@include file="/jsp/user/mainPage.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Show accounts</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/payments" method="post">
    <table class="mainPage">
        <tr>
            <th><fmt:message key="NUMBER_OF_CARD"/></th>
            <th><fmt:message key="STANDARD"/></th>
            <th><fmt:message key="EXPIRATION_DATE"/></th>
            <th><fmt:message key="NUMBER_OF_ACCOUNT"/></th>
        </tr>
        <br>
        <c:forEach items="${sessionScope.cardsList}" var="cardElem">
            <tr>
                <td>
                        ${cardElem.number}
                </td>
                <td>
                        ${cardElem.cardStandard}
                </td>
                <td>
                        ${cardElem.expirationDate}
                </td>
                <td>
                        ${cardElem.account.number}
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>

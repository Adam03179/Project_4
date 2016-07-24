<%@include file="/resources/jsp/user/mainPage.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Open Card</title>
    <link href="<c:url value="/resources/css/openAccount.css"/>" rel="stylesheet">

</head>
<body>
<br>

<div class="mainPage">
    <form action="${pageContext.request.contextPath}/handler" method="post">

        <label for="select-account"><fmt:message key="SELECT_ACCOUNT"/></label>
        <select id="select-account" name="account" required>
            <option disabled selected><fmt:message key="ACCOUNTS"/></option>
            <c:forEach items="${sessionScope.accountsList}" var="accountElem">
                <option>${accountElem.number}</option>
            </c:forEach>
        </select>

        <label for="select-standard"><fmt:message key="SELECT_STANDARD"/></label>
        <select id="select-standard" name="standard" required>
            <option disabled selected><fmt:message key="STANDARD"/></option>
            <option>Master Card</option>
            <option>Visa</option>
            <option>Maestro</option>
            <option>Ukr Card</option>
        </select>

        <label for="pin"><fmt:message key="INPUT_PIN"/></label>
        <input type="text" id="pin" required maxlength="4" name="pin" placeholder="pin">

        <input type="hidden" name="Confirm" value="Attach_Card"/>
        <input type="submit" value=<fmt:message key="ATTACH_CARD"/>>

    </form>

</div>
</body>
</html>

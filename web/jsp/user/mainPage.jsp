<%@page errorPage="errorPage.jsp" %>
<%@include file="/jsp/header.jsp" %>
<%@taglib uri="numberFormatterTag" prefix="numformat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <link href="<c:url value="/css/mainpage.css"/>" rel="stylesheet">

</head>
<body>
<div align="right" class="mainPage">

    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="open_account"/>
        <input type="submit" value=<fmt:message key="OPEN_ACCOUNT"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="show_accounts"/>
        <input type="submit" value=<fmt:message key="SHOW_ACCOUNTS"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="open_card"/>
        <input type="submit" value=<fmt:message key="OPEN_CARD"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="show_cards"/>
        <input type="submit" value=<fmt:message key="SHOW_CARDS"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="account_lock_menu"/>
        <input type="submit" value=<fmt:message key="LOCK_ACCOUNT"/>>
    </form>
    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="operations"/>
        <input type="submit" value=<fmt:message key="OPERATIONS"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="refresh"/>
        <input type="submit" value=<fmt:message key="REFRESH"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
        <input type="hidden" name="Confirm" value="log_out"/>
        <input type="submit" value=<fmt:message key="LOG_OUT"/>>
    </form>
</div>
</body>
</html>

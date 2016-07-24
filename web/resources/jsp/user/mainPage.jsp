<%@include file="/resources/jsp/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
    <link href="<c:url value="/resources/css/mainPage.css"/>" rel="stylesheet">

</head>
<body>
<div align="right" class="mainPage">

    <form action="${pageContext.request.contextPath}/handler" method="post">
        <input type="hidden" name="Confirm" value="open_account"/>
        <input type="submit" value=<fmt:message key="OPEN_ACCOUNT"/>>
    </form>

    <form action="${pageContext.request.contextPath}/handler" method="post">
        <input type="hidden" name="Confirm" value="show_accounts"/>
        <input type="submit" value=<fmt:message key="SHOW_ACCOUNTS"/>>
    </form>

    <form action="${pageContext.request.contextPath}/handler" method="post">
        <input type="hidden" name="Confirm" value="open_card"/>
        <input type="submit" value=<fmt:message key="OPEN_CARD"/>>
    </form>

    <form action="${pageContext.request.contextPath}/handler" method="post">
        <input type="hidden" name="Confirm" value="show_cards"/>
        <input type="submit" value=<fmt:message key="SHOW_CARDS"/>>
    </form>

    <form action="${pageContext.request.contextPath}/handler" method="post">
        <input type="hidden" name="Confirm" value="block_account"/>
        <input type="submit" value=<fmt:message key="BLOCK_ACCOUNT"/>>
    </form>

    <form action="${pageContext.request.contextPath}/handler" method="post">
        <input type="hidden" name="Confirm" value="unblock_account"/>
        <input type="submit" value=<fmt:message key="UNBLOCK_ACCOUNT"/>>
    </form>

    <form action="${pageContext.request.contextPath}/handler" method="post">
        <a href="<c:url value="/resources/jsp/index.jsp"/>" class="cancel">
            <input type="button" name="cancel" value=<fmt:message key="CANCEL"/>>
        </a>
    </form>
</div>
</body>
</html>

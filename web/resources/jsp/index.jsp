<%@include file="/resources/jsp/header.jsp" %>

<html>
<head>
    <title><fmt:message key="LOGIN"/></title>
    <link href="<c:url value="/resources/css/index.css"/>" rel="stylesheet">
</head>
<body>

<form action="${pageContext.request.contextPath}/handler" id="login-form" method="post">
    <div class="login">
        <label for="login-field"><fmt:message key="LOGIN"/></label>
        <input type="email" name="email"  id="login-field" required placeholder=<fmt:message key="LOGIN"/>>
        <br>
        <label for="password-field"><fmt:message key="PASSWORD"/></label>
        <input type="password" name="password" id="password-field" required placeholder=<fmt:message key="PASSWORD"/>>
        <br>
        <input type="hidden" name="Confirm" value="login"/>
        <input type="submit" class="loginSubmit" value=<fmt:message key="ENTER"/>>
    </div>
</form>
<div class="link">
    <a href="<c:url value="/resources/jsp/user/registration.jsp"/>" id="regLink" data-hover="<fmt:message key="REGISTRATION"/>">
        <span><fmt:message key="REGISTRATION"/></span>
    </a>
</div>
</body>
</html>

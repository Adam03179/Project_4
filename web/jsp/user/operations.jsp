<%@include file="/jsp/user/mainPage.jsp" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Operations</title>

</head>
<body>
<br>

<div class="mainPage">

    <form action="${pageContext.request.contextPath}/payments" method="get">
      <input type="hidden" name="Confirm" value="Deposit"/>
      <input type="submit" value=<fmt:message key="DEPOSIT_OPERATION"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="get">
      <input type="hidden" name="Confirm" value="withdraw_funds"/>
      <input type="submit" value=<fmt:message key="WITHDRAW_FUNDS"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="get">
      <input type="hidden" name="Confirm" value="transfer"/>
      <input type="submit" value=<fmt:message key="DO_PAYMENT"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="get">
        <input type="hidden" name="Confirm" value="ch_pass"/>
        <input type="submit" value=<fmt:message key="CHANGE_PASS"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="get">
      <input type="hidden" name="Confirm" value="show_history"/>
      <input type="submit" value=<fmt:message key="SHOW_HISTORY"/>>
    </form>


</div>
</body>
</html>

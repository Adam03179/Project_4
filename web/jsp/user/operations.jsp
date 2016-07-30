<%@include file="/jsp/user/mainPage.jsp" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Operations</title>

</head>
<body>
<br>

<div class="mainPage">
  <form action="${pageContext.request.contextPath}/payments" method="post">

    <form action="${pageContext.request.contextPath}/payments" method="post">
      <input type="hidden" name="Confirm" value="Deposit"/>
      <input type="submit" value=<fmt:message key="DEPOSIT_OPERATION"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
      <input type="hidden" name="Confirm" value="withdraw_funds"/>
      <input type="submit" value=<fmt:message key="WITHDRAW_FUNDS"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
      <input type="hidden" name="Confirm" value="transfer"/>
      <input type="submit" value=<fmt:message key="DO_PAYMENT"/>>
    </form>

    <form action="${pageContext.request.contextPath}/payments" method="post">
      <input type="hidden" name="Confirm" value="show_history"/>
      <input type="submit" value=<fmt:message key="SHOW_HISTORY"/>>
    </form>


  </form>

</div>
</body>
</html>

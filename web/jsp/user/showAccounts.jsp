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
      <th><fmt:message key="NUMBER_OF_ACCOUNT"/></th>
      <th><fmt:message key="INTEREST"/></th>
      <th><fmt:message key="OPEN_DATE"/></th>
      <th><fmt:message key="BALANCE"/></th>
      <th><fmt:message key="CURRENCY"/></th>
      <th><fmt:message key="IS_BLOCKED"/></th>

    </tr>
    <br/>
    <c:forEach items="${sessionScope.accountsList}" var="historyElem">
      <tr>
        <td>
            ${historyElem.number}
        </td>
        <td>
            ${historyElem.interest}
        </td>
        <td>
            ${historyElem.openDate}
        </td>
        <td>
          <numformat:formatNumber format="#,##0.00" number="${historyElem.balance}"/>
        </td>
        <td>
          <fmt:message key="UAH"/>
        </td>
        <td>

          <c:set var="elem" value="${historyElem.blocked}"/>
          <c:set var="val" value="false"/>

          <c:if test="${elem eq val}">
            <fmt:message key="NOT_LOCKED"/>
          </c:if>
          <c:if test="${elem eq not val}">
            <fmt:message key="LOCKED"/>
          </c:if>
        </td>

      </tr>
    </c:forEach>
  </table>
</form>

</body>
</html>

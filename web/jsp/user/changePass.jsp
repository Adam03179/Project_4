<%@include file="/jsp/user/operations.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Change Password</title>
  <script type="text/javascript">
    window.onload = function () {
      document.getElementById("registration-password").onchange = validatePassword;
      document.getElementById("registration-rePassword").onchange = validatePassword;
    };
    function validatePassword() {
      var pass2 = document.getElementById("registration-rePassword").value;
      var pass1 = document.getElementById("registration-password").value;
      if (pass1 != pass2) {
        document.getElementById("registration-rePassword").
                setCustomValidity("<fmt:message key="PASSWORDS_DONT_MATCH"/>");
      }
      else {
        document.getElementById("registration-rePassword").setCustomValidity('');
      }
    }

  </script>
</head>
<body>
<br>

<div class="mainPage">
  <form action="${pageContext.request.contextPath}/payments" method="post">

    <label for="registration-oldPassword"><fmt:message key="PASSWORD"/></label>
    <input type="password" name="oldPassword" required id="registration-oldPassword">
    <br>

    <label for="registration-password"><fmt:message key="NEW_PASSWORD"/></label>
    <input type="password" name="password" required id="registration-password">
    <br>
    <label for="registration-rePassword"><fmt:message key="NEW_PASSWORD"/></label>
    <input type="password" name="rePassword" required id="registration-rePassword">
    <br>

    <input type="hidden" name="Confirm" value="Change_pass"/>
    <input type="submit" value=<fmt:message key="CHANGE_PASS"/>>

  </form>

</div>
</body>
</html>

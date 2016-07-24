<%@include file="/resources/jsp/header.jsp" %>

<html>
<head>
  <title>Success Project4</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link href="<c:url value="/resources/css/inform.css"/>" rel="stylesheet">

</head>
<body>
<div class="wrap">
  <div class="logo">
    <p>Project4</p>
  </div>
  <div class="title">
    <h1><fmt:message key="SUCCESS"/></h1>
    <h2><fmt:message key="CONGRATULATIONS"/></h2>
  </div>
</div>
<div class="wrap">
  <div class="gray">
    <a href="<c:url value="/resources/jsp/user/mainPage.jsp"/>"><fmt:message key="GO_BACK"/></a>
  </div>
</div>

</body>
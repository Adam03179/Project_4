<%@include file="/jsp/header.jsp" %>

<html>
<head>
    <title>Error Project4</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<c:url value="/css/inform.css"/>" rel="stylesheet">

</head>
<body>
<div class="wrap">
     <div class="title">
        <h1><fmt:message key="FAILED"/></h1>
        <h2><fmt:message key="ERROR"/></h2>
    </div>
</div>
<div class="wrap">
    <div class="gray">
        <a href="<c:url value="/jsp/index.jsp"/>"><fmt:message key="GO_BACK"/></a>
    </div>
</div>

</body>
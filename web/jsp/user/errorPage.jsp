<%@include file="/layout/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<core:set var="locale" value="${not empty locale ? locale : pageContext.request.locale}" scope="session"/>

<html>
<head>
    <title>Error Project4</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
    <style type="text/css">
        body{
            background:url(../../img/bg.jpg) ;
        }
        .wrap{
            margin:0 auto;
            width:1000px;
        }
        .title{
            margin-bottom: 40px;
        }
        .title h1{
            font-size:100px;
            color: #b6cde9;
            text-align:center;
            margin-top:100px;
            text-shadow:6px 1px 6px #333;
            font-family: 'Fjalla One', sans-serif;
        }
        .title h2{
            font-size:100px;
            color: #b6cde9;
            text-align:center;
            margin-bottom:1px;
            text-shadow:6px 1px 6px #333;
            font-family: 'Fjalla One', sans-serif;
            margin-top: -20px;
        }
        .logo p{
            color:white;
            font-size:25px;
            margin-top:1px;
            font-family: 'Fjalla One', sans-serif;
        }
        .gray {
            margin-bottom: 20px;
            background: rgba(12, 52, 77, 0.34);
            text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);
            border-radius: 4px;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            -o-border-radius: 4px;
            color: #161971;
            text-decoration:none;
            padding:30px 30px;
            font-size: 20px;
            font-weight:bold;
            font-family: 'Fjalla One', sans-serif;
            text-align: center;
        }

        a{
            position: relative;
            display: inline-block;
            font-size: 90%;
            font-weight: 700;
            color: rgb(209,209,217);
            text-decoration: none;
            text-shadow: 0 -1px 2px rgba(0,0,0,.2);
            padding: .5em 1em;
            outline: none;
            border-radius: 3px;
            background: linear-gradient(rgb(60, 96, 129), rgb(50, 76, 117)) rgb(60, 96, 129);
            box-shadow:
            0 1px rgba(255,255,255,.2) inset,
            0 3px 5px rgba(0,1,6,.5),
            0 0 1px 1px rgba(0,1,6,.2);
            transition: .2s ease-in-out;

        }

        a:active{
            top: 1px;
            background: linear-gradient(rgb(37, 72, 121), rgb(22, 25, 113)) rgb(37, 72, 121);
            box-shadow:
            0 0 1px rgba(0,0,0,.5) inset,
            0 2px 3px rgba(0,0,0,.5) inset,
            0 1px 1px rgba(255,255,255,.1);
        }

        a:hover:not(:active){
            top: 1px;
            background: linear-gradient(rgb(37, 72, 121), rgb(22, 25, 113)) rgb(37, 72, 121);

        }

    </style>
</head>
<body>
<div class="wrap">
    <div class="logo">
        <p>Project4</p>
    </div>
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
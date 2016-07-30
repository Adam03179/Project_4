<%@include file="/jsp/header.jsp" %>

<html>
<head>
    <title><fmt:message key="REGISTRATION"/></title>

    <script type="text/javascript">
        window.onload = function () {
            document.getElementById("registration-password").onchange = validatePassword;
            document.getElementById("registration-repassword").onchange = validatePassword;
        };
        function validatePassword() {
            var pass2 = document.getElementById("registration-repassword").value;
            var pass1 = document.getElementById("registration-password").value;
            if (pass1 != pass2) {
                document.getElementById("registration-repassword").
                        setCustomValidity("<fmt:message key="PASSWORDS_DONT_MATCH"/>");
            }
            else {
                document.getElementById("registration-repassword").setCustomValidity('');
            }
        }

    </script>
    <link href="<c:url value="/css/registrationStyle.css"/>" rel="stylesheet">

</head>
<body>
<div class="registration-class">
    <form action="${pageContext.request.contextPath}/payments" method="post">
        <h1><fmt:message key="GENERAL_INFO"/></h1>
        <label for="registration-name"><fmt:message key="REG_NAME"/></label>
        <input type="text" name="name" required id="registration-name">
        <br>
        <label for="registration-surname"><fmt:message key="REG_SURNAME"/></label>
        <input type="text" name="surname" required id="registration-surname">
        <br>
        <label for="registration-taxnumber"><fmt:message key="TAX_NUMBER"/></label>
        <input type="text" name="taxNumber" required id="registration-taxnumber">
        <br>

        <label for="registration-password"><fmt:message key="PASSWORD"/></label>
        <input type="password" name="password" required id="registration-password">
        <br>
        <label for="registration-repassword"><fmt:message key="PASSWORD"/></label>
        <input type="password" name="repassword" required id="registration-repassword">
        <br>
        <label for="registration-passnum"><fmt:message key="PASSPORT_NUMBER"/></label>
        <input type="text" name="numberOfPassport" required id="registration-passnum">
        <br>
        <label for="registration-passpser"><fmt:message key="PASSPORT_SERIES"/></label>
        <input type="text" name="seriesOfPassport" required id="registration-passpser">

        <h1><fmt:message key="CONTACT_INFO"/></h1>
        <label for="registration-city"><fmt:message key="CITY"/></label>
        <input type="text" name="city" required id="registration-city">
        <br>
        <label for="registration-postcode"><fmt:message key="POST_CODE"/></label>
        <input type="number" name="postCode" required id="registration-postcode">
        <br>
        <label for="registration-street"><fmt:message key="STREET"/></label>
        <input type="text" name="street" required id="registration-street">
        <br>
        <label for="registration-housenum"><fmt:message key="HOUSE_NUMBER"/></label>
        <input type="text" name="numberOfHouse" required id="registration-housenum">
        <br>
        <label for="registration-apnum"><fmt:message key="APARTMENT_NUMBER"/></label>
        <input type="text" name="numberOfApartment" required id="registration-apnum">
        <br>
        <label for="registration-telnum"><fmt:message key="TELEPHONE_NUMBER"/></label>
        <input type="text" name="telephoneNumber" required id="registration-telnum">
        <br>
        <label for="registration-email"><fmt:message key="EMAIL"/></label>
        <input type="email" name="email" required id="registration-email">
        <br>
        <label for="registration-region"><fmt:message key="REGION"/></label>
        <input type="text" name="region" required id="registration-region">
        <br>
        <br>


        <input type="hidden" name="Confirm" value="registration"/>
        <input type="submit" value=<fmt:message key="REGISTRATION"/>>
        <a href="<c:url value="/jsp/index.jsp"/>" class="cancel">
            <input type="button" name="cancel" value=<fmt:message key="CANCEL"/>>
        </a>
    </form>

</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <title><fmt:message key="label.authorization"/></title>
    <style>
        form {
            margin-top: 2%;
            margin-left: 20%;
            font-size: xx-large;
        }
        input {
            margin-top: 2%;
            font-size: x-large;
        }
        fieldset {
            margin-bottom: 20%;
            font-size: larger;
            background-color: rgba(185,134,95,0.3);
        }

        input:invalid {
            border: 2px solid rgba(185, 7, 7, 0.79);
        }

        input:valid {
            border: 2px solid rgba(43, 106, 224, 0.84);
        }

        body {
            background-color: rgba(232, 216, 189, 0.87);
        }
    </style>
</head>
<body>
<h2 style="text-align: left"><fmt:message key="label.periodicals"/></h2>
<hr><br>
<fieldset>
    <legend><fmt:message key="label.enter" /></legend>
    <form action="app" method="post">
        <input type="hidden" name="command" value="logIn"><br>
        <label for="login"><fmt:message key="label.login" /></label>
        <input type="text" required name="login" id="login"/><br/>
        <label for="password"> <fmt:message key="label.pass"/></label>
        <input type="password" required  name="password" id="password" pattern="[0-9a-zA-Z]{6,}" minlength="6" maxlength="20"/><br/>
        <p style="margin-left: 10%; font-size: small"><fmt:message key="label.valid"/></p>

    <c:if test="${param.wrong == 1}">
        <h3 style="margin-left: 12%; font-size: large; color: brown"><fmt:message key="label.notValid"/></h3>
    </c:if>
        <input value="<fmt:message key="label.logIn" />" type="submit"/>
    </form>
</fieldset>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>

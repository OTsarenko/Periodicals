<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <title><fmt:message key="label.registration"/></title>
    <style>
        table {
            font-size: x-large;
        }
        fieldset {
            font-size: larger;
            margin-top: 5%;
            margin-bottom: 20%;
            background-color: rgba(185,134,95,0.3);
        }
        input {
            font-size: large;
        }

        input:invalid {
            border: 2px solid rgba(232, 2, 2, 0.45);
        }

        input:valid {
            border: 2px solid rgba(61, 61, 238, 0.84);
        }

        body {
            background-color: rgba(232, 216, 189, 0.87);
        }
    </style>
</head>
<body >
<div>
    <h2 style="float: left"><fmt:message key="label.periodicals"/></h2>
    <p style="text-align: right;">
        <a href="app?command=logInForm"><fmt:message key="label.authorization"/></a>
    </p>
</div>
<hr>
<fieldset>
    <legend><fmt:message key="label.registration" /></legend>

    <form action="app" method="post">
        <input type="hidden" name="command" value="registration"><br>
        <table style="margin-left: 20%">
            <tr>
                <td><fmt:message key="label.login"/></td>
                <td><label>
                    <input type="text" name="login" required maxlength="20">
                </label></td>
            </tr>
            <tr>
                <td>E-mail</td>
                <td><label>
                    <input type="email" name="email"  required maxlength="20">
                </label></td>
            </tr>
            <tr>
                <td><fmt:message key="label.pass"/></td>
                <td><label>
                    <input type="password" required  name="password" id="password" pattern="[0-9a-zA-Z]{6,}" minlength="6" maxlength="20"/>
                </label></td>
            </tr>
        </table>
        <p style="margin-left: 25%; font-size: small"><fmt:message key="label.valid"/></p>

        <c:if test="${param.wrong == 1}">
            <h3 style="margin-left: 25%; font-size: large; color: brown"><fmt:message key="label.logEmExist"/></h3>
        </c:if>
        <c:if test="${param.wrong == 2}">
            <h3 style="margin-left: 25%; font-size: large; color: brown"><fmt:message key="label.loginExists"/></h3>
        </c:if>
        <c:if test="${param.wrong == 3}">
            <h3 style="margin-left: 25%; font-size: large; color: brown"><fmt:message key="label.emailExists"/></h3>
        </c:if>
        <p style="margin-left: 20%; font-size: x-large;"><input type="submit" value="<fmt:message key="label.registration" />"></p>
    </form>
</fieldset>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>

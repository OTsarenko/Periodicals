<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <title><fmt:message key="label.personalAcc"/>Title</title>
    <style>
        body {
            background-color: rgba(232, 216, 189, 0.87);
        }
    </style>
</head>
<body>
<%@include file="../fragments/headerU.jspf" %>
<div style="margin-bottom: 5%; margin-left: 10%; font-size: x-large; margin-top: 3%; background-color: rgba(185,134,95,0.3); max-width: 1120px;display: flex;justify-content: center;flex-wrap: wrap;">
    <img src="photo/006.png" alt="User" style="width: 10%; margin-right: 5%">
    <fmt:message key="label.login" />: <c:out value="${sessionScope.user.login}"/><br>
    E-mail: <c:out value="${sessionScope.user.email}"/><br>
    Facebook: <c:out value="${sessionScope.user.facebook}"/><br>
    <fmt:message key="label.language" />:<c:out value="${sessionScope.user.language}"/><br>
    <fmt:message key="label.role" />:<c:out value="${sessionScope.user.userRole}"/><br>
</div>
<fieldset>
    <legend style="font-size: x-large"><fmt:message key="label.edit" /></legend>
    <form action="app" method="post">
        <input type="hidden" name="command" value="editUser"><br>
        <label for="login"><fmt:message key="label.login" /></label>
        <input type="text" name="login" id="login"/><br/>
        <label for="password"> <fmt:message key="label.pass"/></label>
        <input type="password"  name="password" id="password" pattern="[0-9a-zA-Z]{6,}" minlength="6" maxlength="20"/><br/>
        <p style="margin-left: 3%; font-size: small"><fmt:message key="label.valid"/></p>
        <label for="email">E-mail</label>
        <input type="email" name="email" id="email"/><br/>
        <label for="facebook">Facebook</label>
        <input type="text" name="facebook" id="facebook"/><br/>
        <label for="language"><fmt:message key="label.language" /></label>
        <input type="radio" name="language" value="ENG" id="language"  />ENG
        <input type="radio" name="language" value="UKR" id="language" checked />UKR<br/>

        <c:if test="${param.wrong == 1}">
            <h3 style="margin-left: 25%; font-size: large; color: brown"><fmt:message key="label.loginExists"/></h3>
        </c:if>
        <c:if test="${param.wrong == 2}">
            <h3 style="margin-left: 25%; font-size: large; color: brown"><fmt:message key="label.emailExists"/></h3>
        </c:if>
        <input value="<fmt:message key="label.edit" />" type="submit"/>
        <c:if test="${param.wrong == 0}">
            <h3 style="margin-left: 25%; font-size: large; color: brown"><fmt:message key="label.changed"/></h3>
        </c:if>
    </form>
</fieldset>
<div style="font-size: x-large; margin-top: 3%">
<fmt:message key="label.look"/>:
    <button style="font-size: x-large"><a href="app?command=allUsers"><fmt:message key="label.users"/></a></button>
    <button style="font-size: x-large"><a href="app?command=periodicalsForAdmin&page=1"><fmt:message key="label.periodicals"/></a></button>
    <button style="font-size: x-large"><a href="app?command="><fmt:message key="label.home"/></a></button>
</div>
</body>
</html>

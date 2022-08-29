<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.personalAcc"/> </title>
    <style>
        table {
            font-size: larger;
            border: 1px solid black;
            border-spacing: 7px 2px;
        }

        td {
            border: 1px solid black;
            padding: 0 15px;
        }
        body {
            background-color: rgba(232, 216, 189, 0.87);
        }
    </style>
</head>
<body>
<div>
    <h2 style="float: left"><fmt:message key="label.periodicals"/></h2>
    <p style="text-align: right;">
        <a href="app?command="><fmt:message key="label.home"/></a> |
        <a href="app?command=logOut"><fmt:message key="label.exit"/></a> |
        <a href="?command=setLocale&locale&com=${param.command}">ENG</a> |
        <a href="?command=setLocale&locale=uk&com=${param.command}">UKR</a>
    </p>
</div>
<hr>
<div style="margin-bottom: 5%; margin-left: 10%; font-size: x-large; margin-top: 3%; background-color: rgba(185,134,95,0.3); max-width: 1120px;display: flex;justify-content: center;flex-wrap: wrap;">
    <img src="photo/006.png" alt="User" style="width: 10%; margin-right: 5%">
    <fmt:message key="label.login" />: <c:out value="${sessionScope.user.login}"/><br>
    E-mail: <c:out value="${sessionScope.user.email}"/><br>
    <fmt:message key="label.language" />:<c:out value="${sessionScope.user.language}"/><br>
    <fmt:message key="label.role" />:<c:out value="${sessionScope.user.userRole}"/><br>
    <fmt:message key="label.account" />:<c:out value="${sessionScope.user.account}"/><br>
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
<fieldset>
    <legend style="font-size: x-large"><fmt:message key="label.replenish" /></legend>
    <form action="app" method="post">
        <input type="hidden" name="command" value="replenishAccount"><br>
        <label for="account"><fmt:message key="label.account" /></label>
        <input type="number" name="sum" id="account"/><br/>
        <input value="<fmt:message key="label.replenish" />" type="submit"/>
        <c:if test="${param.wrong == -1}">
            <h3 style="margin-left: 25%; font-size: large; color: brown"><fmt:message key="label.replenished"/></h3>
        </c:if>
    </form>
</fieldset>
<c:if test="${sessionScope.user.message != null}">
    <h2 style="text-align: center"><c:out value="${sessionScope.user.message}"/></h2>
</c:if>
<c:if test="${periodicals != null}">
<table style="width: 99%; margin-bottom: 5%; background-color: rgba(232, 216, 189, 0.87); margin-top: 2%" class="periodicals">
    <caption style="font-size: x-large"><fmt:message key="label.subscribes"/></caption>
    <tr style="text-align: center">
        <th><fmt:message key="label.title"/></th>
        <th><fmt:message key="label.description"/></th>
        <th><fmt:message key="label.issue"/></th>
    </tr>
    <c:forEach var="periodicals" items="${periodicals}">
        <tr>
            <td><c:out value="${periodicals.getEngTitle()}"/></td>
            <td><c:out value="${periodicals.getEngDescription()}"/></td>
            <td><c:out value="${periodicals.getIssue()}"/></td>
        </tr>
    </c:forEach>
</table>
</c:if>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <style>
        form {
            font-size: larger;
            text-align: center;
            margin-bottom: 20%;
        }

        select {
            font-size: larger;
            border: 1px solid black;
            border-spacing: 7px 2px;
        }

        body {
            background-color: rgba(232, 216, 189, 0.87);
        }
    </style>
    <meta charset="UTF-8">
    <title><fmt:message key="label.subscribe"/></title>
</head>
<body>
<c:if test="${user == null}">
    <%@include file="../fragments/header.jspf" %>
</c:if>
<c:if test="${user != null}">
    <%@include file="../fragments/headerU.jspf" %>
</c:if>
<img src="photo/003.jpg" alt="Периодика" width="52%" style="float: right">
<div style="margin-left: 5%; font-size: x-large; margin-top: 10%; background-color: rgba(185,134,95,0.3); max-width: 1120px;display: flex;justify-content: center;flex-wrap: wrap;">
    <c:if test="${theLocale != 'uk'}">
        <c:out value="${periodical.engTitle}"/><br>
        <c:out value="${periodical.engDescription}"/><br>
    </c:if>
    <c:if test="${theLocale == 'uk'}">
        <c:out value="${periodical.ukrTitle}"/><br>
        <c:out value="${periodical.ukrDescription}"/><br>
    </c:if>
    <c:out value="${periodical.price} UAH"/>
</div>
<form action="app" method="post">
    <input type="hidden" name="command" value="subscribe"><br>
    <input type="hidden" name="id" value="${periodical.id}"><br>
Amount: <select name="amount">
    <option>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
    <option>5</option>
    <option>6</option>
    <option>7</option>
    <option>8</option>
    <option>9</option>
    <option>10</option>
    <option>11</option>
    <option>12</option>
</select>
    <button style="font-size: large" type="submit"><fmt:message key="label.subscribe" /></button>
</form>

<%@include file="../fragments/footer.jspf" %>
</body>
</html>

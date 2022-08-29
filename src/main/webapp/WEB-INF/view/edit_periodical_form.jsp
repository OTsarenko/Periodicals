<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <title><fmt:message key="label.edit"/></title>
    <style>
        form {
            font-size: large;
        }
        body {
            background-color: rgba(232, 216, 189, 0.87);
        }
    </style>
</head>
<body>
<%@include file="../fragments/headerU.jspf" %>
<fieldset style="margin-left: 2%; margin-top: 5%; margin-bottom: 2%; border: 1px solid black; padding: 2px 550px;">
    <legend style="font-size: x-large"><fmt:message key="label.edit" /></legend>
    <form action="app" method="post">
        <input type="hidden" name="command" value="editPeriodical"><br>
        <input type="hidden" name="id" value="${periodical.id}"><br>
        <label for="engTitle"><fmt:message key="label.engTitle" /></label>
        <input type="text" name="engTitle" value="${periodical.engTitle}" minlength="2" maxlength="255"  id="engTitle"/><br/>
        <label for="ukrTitle"><fmt:message key="label.ukrTitle" /></label>
        <input type="text" name="ukrTitle" value="${periodical.ukrTitle}" minlength="2" maxlength="255"  id="ukrTitle"/><br/>
        <label for="engDescription"><fmt:message key="label.engDescription" /></label>
        <input type="text" name="engDescription" value="${periodical.engDescription}" minlength="2" maxlength="255"  id="engDescription"/><br/>
        <label for="ukrDescription"><fmt:message key="label.ukrDescription" /></label>
        <input type="text" name="ukrDescription" value="${periodical.ukrDescription}" minlength="2" maxlength="255"  id="ukrDescription"/><br/>
        <label for="price"><fmt:message key="label.price" /></label>
        <input type="number" name="price" value="${periodical.price}"  id="price"/><br/>
        <input value="<fmt:message key="label.edit" />" type="submit"/>
    </form>
</fieldset>
<fieldset style="margin-left: 2%; border: 1px solid black; padding: 2px 550px;">
    <legend style="font-size: x-large"><fmt:message key="label.nextIssue" /></legend>
    <form action="app" method="post">
        <input type="hidden" name="command" value="editIssue"><br>
        <input type="hidden" name="id" value="${periodical.id}"><br>
        <label for="issue"><fmt:message key="label.nextIssue" /></label>
        <input type="checkbox" checked name="issue" value="${periodical.issue +1}"  id="issue"/><br/>

        <input value="<fmt:message key="label.nextIssue" />" type="submit"/>
    </form>
</fieldset>
</body>
</html>

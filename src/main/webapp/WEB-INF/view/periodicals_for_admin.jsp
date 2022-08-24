<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.periodicals"/> </title>
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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@include file="../fragments/headerU.jspf" %>
<fieldset style="margin-left: 2%; border: 1px solid black; padding: 2px 550px;">
    <legend style="font-size: x-large"><fmt:message key="label.addPeriod" /></legend>
    <form action="app" method="post">
        <input type="hidden" name="command" value="addPeriodical"><br>
        <label for="engTitle"><fmt:message key="label.engTitle" /></label>
        <input type="text" name="engTitle" minlength="2" maxlength="255" required id="engTitle"/><br/>
        <label for="ukrTitle"><fmt:message key="label.ukrTitle" /></label>
        <input type="text" name="ukrTitle" minlength="2" maxlength="255" required id="ukrTitle"/><br/>
        <label for="engDescription"><fmt:message key="label.engDescription" /></label>
        <input type="text" name="engDescription" minlength="2" maxlength="255" required id="engDescription"/><br/>
        <label for="ukrDescription"><fmt:message key="label.ukrDescription" /></label>
        <input type="text" name="ukrDescription" minlength="2" maxlength="255" required id="ukrDescription"/><br/>
        <label for="issue"><fmt:message key="label.issue" /></label>
        <input type="number" name="issue" required id="issue"/><br/>
        <label for="price"><fmt:message key="label.price" /></label>
        <input type="number" name="price" required id="price"/><br/>
        <details>
            <summary style="font-size: large"><fmt:message key="label.topic" /></summary>

            <c:forEach var="topics" items="${topics}">
                <ul>
                    <c:if test="${theLocale != 'uk'}">
                        <input type="checkbox" name="topic" value="${topics.id}" />${topics.engTopicName}
                    </c:if>
                    <c:if test="${theLocale == 'uk'}">
                        <input type="checkbox" name="topic" value="${topics.id}" />${topics.ukrTopicName}
                    </c:if>
                </ul>
            </c:forEach>

        </details>
        <input value="<fmt:message key="label.addPeriod" />" type="submit"/>
        <c:if test="${param.wrong == 1}">
            <h3 style="margin-left: 25%; font-size: large; color: brown"><fmt:message key="label.cantAdd"/></h3>
        </c:if>
    </form>
</fieldset>

<table style="width: 99%; background-color: rgba(232, 216, 189, 0.87); margin-top: 3%" class="periodicals">
    <caption><fmt:message key="label.periodicals"/></caption>
    <tr style="text-align: center">
        <th>Id</th>
        <th><fmt:message key="label.title"/></th>
        <th><fmt:message key="label.description"/></th>
        <th><fmt:message key="label.issue"/></th>
        <th><fmt:message key="label.price"/></th>
    </tr>
    <c:forEach var="periodicals" items="${periodicals}">
        <tr>
            <c:if test="${theLocale != 'uk'}">
                <td><c:out value="${periodicals.getId()}"/></td>
                <td><c:out value="${periodicals.getEngTitle()}"/></td>
                <td><c:out value="${periodicals.getEngDescription()}"/></td>
                <td><c:out value="${periodicals.getIssue()}"/></td>
                <td><c:out value="${periodicals.getPrice()}"/></td>
            </c:if>
            <c:if test="${theLocale == 'uk'}">
                <td><c:out value="${periodicals.getId()}"/></td>
                <td><c:out value="${periodicals.getUkrTitle()}"/></td>
                <td><c:out value="${periodicals.getUkrDescription()}"/></td>
                <td><c:out value="${periodicals.getIssue()}"/></td>
                <td><c:out value="${periodicals.getPrice()}"/></td>
            </c:if>
            <td><a style="text-align: center; color: rgba(51,109,197,0.85); background-color: rgba(185,134,95,0.3)" class="page-link" href="app?command=deletePeriodical&id=${periodicals.getId()}"><fmt:message key="label.delete"/></a></td>
            <td><a style="text-align: center; color: rgba(51,109,197,0.85); background-color: rgba(185,134,95,0.3)" class="page-link" href="app?command=editPeriodicalForm&id=${periodicals.getId()}"><fmt:message key="label.edit"/></a></td>
        </tr>
    </c:forEach>
</table>
<br>
<nav>
    <ul class="pagination justify-content-center">
        <c:if test="${param.page-1 >= 1}">
            <li class="page-item"><a class="page-link" href="app?command=${param.command}&page=${param.page-1}"><fmt:message key="label.previous"/></a></li>
        </c:if>
        <c:forEach var="page" items="${pages}">
            <li class="page-item"><a class="page-link" href="app?command=${param.command}&page=${page}">${page}</a></li>
        </c:forEach>
        <c:set var="size" scope="page" value="${requestScope.pages}"/>
        <c:if test="${param.page+1 <= size.size()}">
            <li class="page-item"><a class="page-link" href="app?command=${param.command}&page=${param.page+1}"><fmt:message key="label.next"/></a></li>
        </c:if>
    </ul>
</nav>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>

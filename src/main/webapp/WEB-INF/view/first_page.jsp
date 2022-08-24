<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.periodicals"/></title>
    <style>
        li {text-align: left;
            font-size: 14pt;
        }

        table {
            font-size: larger;
            border: 1px solid black;
            border-spacing: 7px 2px;
            width: 100%;
        }

        td {
            border: 1px solid black;
            padding: 0 15px;
        }

        body {
            background-color: rgba(232, 216, 189, 0.87);
        }
    </style>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${user == null}">
    <%@include file="../fragments/header.jspf" %>
</c:if>
<c:if test="${user != null}">
    <%@include file="../fragments/headerU.jspf" %>
</c:if>

<form action="app" method="get" >
    <img src="photo/005.png" alt="Периодика" width="25%" style="float:right; margin-right:10%;">
    <input type="hidden" name="command" value="findPeriodical"><br>
    <input type="search" name="title" placeholder="   ">
    <button type="submit"><fmt:message key="label.search" /></button>
</form>
<div style="margin-left: 3%">
<h1><fmt:message key="label.can" /></h1>
    <details>
        <summary style="font-size: large"><fmt:message key="label.topics" /></summary>

            <c:forEach var="topics" items="${topics}">
                <ul>
                    <c:if test="${theLocale != 'uk'}">
                        <li><a href="app?command=periodicalsByTopic&page=1&id=${topics.id}">${topics.engTopicName}</a></li>
                    </c:if>
                    <c:if test="${theLocale == 'uk'}">
                        <li><a href="app?command=periodicalsByTopic&page=1&id=${topics.id}">${topics.ukrTopicName}</a></li>
                    </c:if>
                </ul>
            </c:forEach>

    </details>
    <ul>
            <li><a href="${pageContext.request.contextPath}/app?command=allPeriodicalsByPrice&page=1"><fmt:message key="label.selectPeriodical" /></a></li>
    </ul>
<h3><fmt:message key="label.must" />  <a href="${pageContext.request.contextPath}/app?command=registrationForm"><fmt:message key="label.register" /></a>  <fmt:message key="label.toSubscribe" /></h3>
</div>
<table class="periodicals">
    <tr style="text-align: center">
        <th><fmt:message key="label.topic"/></th>
        <th><fmt:message key="label.title"/></th>
    </tr>
<c:forEach items="${periodicals}" var="per">
    <tr style="background-color: rgba(185,134,95,0.3)">
        <c:if test="${theLocale != 'uk'}">
        <td style="text-align: center"><c:out value="${per.key.engTopicName}"/>
        </td>
        </c:if>
        <c:if test="${theLocale == 'uk'}">
            <td style="text-align: center"><c:out value="${per.key.ukrTopicName}"/>
            </td>
        </c:if>
        <td>
            <c:forEach items="${per.value}" var="v">
                <c:if test="${theLocale != 'uk'}">
                    <ul>
                        <li><c:out value="${v.engTitle}"/></li>
                    </ul>
                </c:if>
                <c:if test="${theLocale == 'uk'}">
                    <ul>
                        <li><c:out value="${v.ukrTitle}"/></li>
                    </ul>
                </c:if>
            </c:forEach>
        </td>
    </tr>
</c:forEach>
</table>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>

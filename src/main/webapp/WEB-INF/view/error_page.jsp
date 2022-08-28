<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <title><fmt:message key="label.error"/></title>
    <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${user == null}">
    <%@include file="../fragments/header.jspf" %>
</c:if>
<c:if test="${user != null}">
    <%@include file="../fragments/headerU.jspf" %>
</c:if>
<table>
    <tr >
        <td>
            <h2><fmt:message key="label.wrong"/></h2>
            <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
            <p>${exception.getMessage() }</p>
            <c:if test="${wrong == '101' }">
                <h2><fmt:message key="label.access"/></h2>
            </c:if>
            <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
            <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>


            <c:if test="${not empty code}">
                <h3>Error code: ${code}</h3>
            </c:if>

            <c:if test="${not empty message}">
                <h3>Message: ${message}</h3>
            </c:if>

        </td>
    </tr>
</table>
</body>
</html>

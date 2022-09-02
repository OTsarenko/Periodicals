<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${theLocale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${theLocale}">
<head>
    <title><fmt:message key="label.users"/></title>
    <style>
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
</head>
<body>
<%@include file="../fragments/headerU.jspf" %>
<table style="width: 99%; background-color: rgba(232, 216, 189, 0.87)">
    <caption style="font-size: x-large"><fmt:message key="label.users"/></caption>
    <tr style="text-align: center">
        <th>ID</th>
        <th><fmt:message key="label.login"/></th>
        <th>E-mail</th>
        <th><fmt:message key="label.language"/></th>
        <th><fmt:message key="label.role"/></th>
        <th><fmt:message key="label.status"/></th>
    </tr>
    <c:forEach var="users" items="${users}">
        <tr>
            <td><c:out value="${users.id}"/></td>
            <td><c:out value="${users.login}"/></td>
            <td><c:out value="${users.email}"/></td>
            <td><c:out value="${users.language}"/></td>
            <td><c:out value="${users.userRole}"/></td>
            <td><c:out value="${users.status}"/></td>
            <c:if test="${users.status == 'ACTIVE'}">
            <td><a style="text-align: center; color: rgba(51,109,197,0.85); background-color: rgba(185,134,95,0.3)" class="page-link" href="app?command=blockingUser&id=${users.getId()}"><fmt:message key="label.block"/></a></td>
            </c:if>
            <c:if test="${users.status == 'BLOCKED'}">
                <td><a style="text-align: center; color: rgba(51,109,197,0.85); background-color: rgba(185,134,95,0.3)" class="page-link" href="app?command=unblockingUser&id=${users.getId()}"><fmt:message key="label.unblock"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>

</body>
</html>

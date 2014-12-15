<%-- 
    Document   : editUser
    Created on : 07.12.2014, 0:01:38
    Author     : SSL-DEX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET" action="<c:url value="/user/userEditSave" />">
            <input type="hidden" name="id" value="${user.getId()}" />
            <label for="name">Name:</label>
            <input type="text" name="name" value="${user.getName()}" /><br/>
            <label for="email">Email:</label>
            <input type="text" name="email" value="${user.getEmail()}"/><br/>
            <label for="password">Password:</label>
            <input type="text" name="password" value="${user.getPassword()}" /><br/>
            <label for="isAdmin">Is admin:</label>
            <input type="checkbox" name="isAdmin" value="${user.isIsAdmin()}"<c:if test="${user.isAdmin}">checked=""</c:if>/><br/>
            <input type="submit" />
        </form>
    </body>
</html>
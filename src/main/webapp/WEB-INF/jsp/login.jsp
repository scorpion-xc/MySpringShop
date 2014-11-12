<%-- 
    Document   : user
    Created on : Nov 9, 2014, 3:10:01 PM
    Author     : andrii
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
    </head>
    <body>
        <form action="<c:url value="/j_spring_security_check" />" method="post">
            <label>Email: <input type="text" name="j_username" /></label>
            <label>Password: <input type="password" name="j_password" /></label>
            <input type="submit" />
        </form>
    </body>
</html>

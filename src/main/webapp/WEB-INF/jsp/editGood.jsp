<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
        <title>Редактирование товара</title>
    </head>
    <body>
        <form method="GET" action="<c:url value="/shop/goodEditSave/${good.getId()}"/>">
            <input type="hidden" name="id" value="${good.getId()}" />
            <label for="name">Name:</label>
            <input type="text" name="name" value="${good.getName()}" />}<br/>
            <label for="desc">Description:</label>
            <input type="text" name="desc" value="${good.getDescription()}" />}<br/>
            <label for="price">Price:</label>
            <input type="text" name="price" value="${good.getPrice()}"/>}<br/>
            <label for="cat">Category:</label>
            <select name="cat">
                <c:forEach items="${cats}" var ="cat">
                    <option <c:if test="${good.category_id} == ${cat.id}">selected</c:if>value="${cat.id}">${cat.name}</option>
                </c:forEach>
            </select>}<br/>
            <input type="submit" />
        </form>
    </body>
</html>

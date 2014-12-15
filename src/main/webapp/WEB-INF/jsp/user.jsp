<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User info page</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
    </head>
    <body>
        
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value="/user/login" />"> Login</a> </li>  
                    <li><a href="<c:url value="/shop" />">Browse categories</a></li>
                    <li><a href="<c:url value="/user/show" />">Profile</a></li>   
                    <li><a href="<c:url value="/user/logout" />">Logout</a></li>
                </ul>
            </div>
        </nav>
        
        <div class="container">
            <div class="jumbotron">
                <h1>${current.name}'s profile</h1>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Selected User</h3>
                        </div>
                        <div class="panel-body">
//----------  
                            <div>
                                <c:if test="${current.isAdmin}">
                                    <c:forEach items="${users}" var="user">
                                        <li>${user.name}<br/>
                                            id: ${user.id}<br/>
                                            e-mail: ${user.email}<br/>
                                            Password: ${user.password}<br/>
                                            Admin: <c:if test="${user.isAdmin}">Yes </c:if><c:if test="${!user.isAdmin}">No </c:if><br/>
                                            <a class="btn btn-info" href="<c:url value="/user/editUser/${user.id}" />">Edit</a>
                                            <a class="btn btn-danger" href="<c:url value="/user/delete/${user.id}" />">Delete</a><br/></li>
                                    </c:forEach> 
                                    <br/><div><a class="btn btn-success" href="<c:url value="/user/addUser" />">Add user</a></div> 
                                </c:if>
                            </div>
                            <div>
                                <c:if test="${!current.isAdmin}">
                                    ${current.name}, this information is closed for you!
                                </c:if>
                            </div>
//----------  
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Current User</h3>
                        </div>
                        <div class="panel-body">
                            <div>Name: ${current.name}</div>
                            <div>e-mail: ${current.email}</div>
                            <div>Admin: ${current.isAdmin}</div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

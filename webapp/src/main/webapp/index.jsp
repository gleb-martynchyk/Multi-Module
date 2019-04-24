<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
</head>
<body>
<h2>Welcome</h2>
<form method="post" action="welcome">
    <div>
        Login:<br>
        <input type="text" name="login" value="admin">
    </div>
    <div>
        Password:<br>
        <input type="password" name="password">
    </div>
    <div>
        <c:if test="${errorMessage != null}">
            <b>${errorMessage}</b>
        </c:if>
        <br>
        <input type="submit" value="Login">
    </div>
</form>
</body>
</html>

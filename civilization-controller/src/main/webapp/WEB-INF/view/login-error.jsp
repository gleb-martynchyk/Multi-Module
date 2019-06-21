<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<div align="center">
    <h2>Welcome</h2>

    Invalid username and password.


    <form method="post" th:action="@{/login}">
        <div>
            Login:<br>
            <input type="text" name="username" value="user" class="form-control-sm">
        </div>
        <div>
            Password:<br>
            <input type="password" name="password" class="form-control-sm">
        </div>
        <div>
            <c:if test="${errorMessage != null}">
                <b>${errorMessage}</b>
            </c:if>
            <br>
            <input type="submit" value="Sign In" class="btn btn-primary">
        </div>
    </form>

</div>
</body>
</html>

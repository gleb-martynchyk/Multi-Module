<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Треугольник</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container" align="center">
    <h4>Нахождение площади треугольника</h4>
    <div>
        <form method="post" action="triangle-area">
            <div>
                <input type="text" name="input" placeholder="X1" size="2">
                <input type="text" name="input" placeholder="Y1" size="2">
            </div>
            <div>
                <input type="text" name="input" placeholder="X2" size="2">
                <input type="text" name="input" placeholder="Y2" size="2">
            </div>
            <div>
                <input type="text" name="input" placeholder="X3" size="2">
                <input type="text" name="input" placeholder="Y3" size="2">
            </div>
            <div>
                <br>
                <input type="submit" value="Рассчитать">
            </div>
        </form>
    </div>
    <c:choose>
        <c:when test="${area ==null}">
        </c:when>
        <c:when test="${area >0}">
            <div class="row">
                <div class="col">
                    <b>Площадь: ${area}</b>
                </div>
                <div class="col">
                    <b>Периметр ${perimeter}</b>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <b>Неверно введены данные</b>
            <br/>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TriangleAjax</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/triangle.js" type="text/javascript"></script>
</head>
<body>
<div class="container" align="center">
    <h4>Нахождение площади треугольника</h4>
    <div>
        <form>
            <div>
                <input type="text" id="x0" placeholder="X1" size="2" class="form-control-sm">
                <input type="text" id="y0" placeholder="Y1" size="2" class="form-control-sm">
            </div>
            <div>
                <input type="text" id="x1" placeholder="X2" size="2" class="form-control-sm">
                <input type="text" id="y1" placeholder="Y2" size="2" class="form-control-sm">
            </div>
            <div>
                <input type="text" id="x2" placeholder="X3" size="2" class="form-control-sm">
                <input type="text" id="y2" placeholder="Y3" size="2" class="form-control-sm">
            </div>
            <div>
                <br>
            </div>
        </form>
    </div>
    <button id="calculate" class="btn btn-primary">Рассчитать</button>
    <b><br><br>Ответ:<br></b>
    <div id="ajaxGetUserServletResponse">
    </div>

    <br/>
    <form action="applications.jsp">
        <input type="submit" value="Вернуться к списку задач" class="btn btn-secondary"/>
    </form>
</div>
</body>
</html>

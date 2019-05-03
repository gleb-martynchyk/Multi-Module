<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div align="center">
    <form method="post" action="">
        <intput type="submit"></intput>
    </form>

    <form action="triangle.jsp">
        <input type="submit" value="Площадь треугольника" class="btn btn-secondary"/>
    </form>

    <form action="triangleAjax.jsp">
        <input type="submit" value="Площадь треугольника Ajax" class="btn btn-secondary"/>
    </form>

    <form action="encryption.jsp">
        <input type="submit" value="Шифрование текста" class="btn btn-secondary"/>
    </form>
</div>
</body>
</html>

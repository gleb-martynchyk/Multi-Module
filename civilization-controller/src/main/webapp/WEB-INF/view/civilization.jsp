<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Civilization</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="my-auto">
        <h4>Civilization - ${civilization.name}</h4>
        <br>
        <div class="row">
            <div class="col-sm-4">
                <table class="table table-hover table-bordered w-auto">
                    <tbody>
                    <tr>
                        <td>Name</td>
                        <td>${civilization.name}</td>
                    </tr>
                    <tr>
                        <td>Capital</td>
                        <td>${civilization.capital.name}</td>
                    </tr>
                    <tr>
                        <td>Gold</td>
                        <td>${civilization.gold}</td>
                    </tr>
                    <tr>
                        <td>Faith</td>
                        <td>${civilization.faith}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-sm-4">
                <table class="table table-bordered w-auto">
                    <h5>Cities</h5>
                    <c:forEach items="${civilization.cities}" var="item">
                        <tr>
                            <td>Name: ${item.name}</td>
                            <td>Level: ${item.level}</td>
                            <td>Defence: ${item.defence}</td>
                            <td>Health: ${item.healthPoint}</td>
                        </tr>
                    </c:forEach>
                </table>
                <form action="/welcome" method="get">
                    <button type="submit" class="btn btn-secondary">Add City</button>
                </form>
            </div>
        </div>

    </div>
    <div class="mt-5">
        <form action="/civilizations" method="get">
            <button type="submit" class="btn btn-primary">Civilizations list</button>
        </form>
    </div>

</div>
</body>
</html>

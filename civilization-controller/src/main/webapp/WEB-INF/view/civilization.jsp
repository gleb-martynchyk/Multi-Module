<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <h4>Civilization - ${civilization.name} #${civilization.id}</h4>
        <br>
        <div class="row">
            <div class="col-sm-4">
                <div class="table-responsive">
                    <table class="table table-hover table-bordered w-auto">
                        <tbody>
                        <tr>
                            <th>Name</th>
                            <td>${civilization.name}</td>
                        </tr>
                        <tr>
                            <th>Capital</th>
                            <td>${civilization.capital.name}</td>
                        </tr>
                        <tr>
                            <th>Gold</th>
                            <td>${civilization.gold}</td>
                        </tr>
                        <tr>
                            <th>Faith</th>
                            <td>${civilization.faith}</td>
                        </tr>
                        <tr>
                            <th>Science</th>
                            <td>${civilization.science}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-sm-4">
                <table class="table table-bordered w-auto">
                    <h5>Cities</h5>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Level</th>
                        <th scope="col">Defence</th>
                        <th scope="col">Health</th>
                        <th scope="col">Units</th>
                        <th scope="col">Strength</th>
                        <th scope="col">Food</th>
                        <th scope="col">Production</th>
                    </tr>
                    <c:forEach items="${civilization.cities}" var="item">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.level}</td>
                            <td>${item.defence}</td>
                            <td>${item.healthPoint}</td>
                            <td>${fn:length(item.units)}
                                <a class="btn btn-outline-primary btn-sm my-0"
                                   href="civilizations/${civilization.id}" role="button">View</a>
                            </td>
                            <td>${item.strength}</td>
                            <td>${item.getFood()}</td>
                            <td>${item.getProduction()}</td>
                        </tr>
                    </c:forEach>
                </table>
                <form action="/city_form" method="get">
                    <button type="submit" class="btn btn-secondary ">Add City</button>
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

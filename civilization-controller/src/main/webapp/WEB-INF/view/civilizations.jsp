<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Civilization</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="civilizations.js"></script>
</head>

<body>
<div class="container mt-5">
    <div class="my-auto">

        <h3>Civilizations</h3>
        <br>
        <c:if test="${error != ''}">
            <h3 align="center">${error} </h3>
        </c:if>
        <br>

        <ul class="list-group">
            <table class="table table-hover table-bordered w-auto">
                <h5>Cities</h5>
                <thead class="thead-light">
                <tr>
                    <th scope="col">#id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Gold</th>
                    <th scope="col">Faith</th>
                    <th scope="col">Science</th>
                    <th scope="col">Army</th>
                    <th scope="col">Population</th>
                    <th scope="col">Capital</th>
                    <th scope="col">Cities</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${civilizationsList}" var="item">
                    <tr>
                        <td class="align-middle">${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.gold}</td>
                        <td>${item.faith}</td>
                        <td>${item.science}</td>
                        <td>${item.getWarriorUnitsSize()}</td>
                        <td>${item.getPopulation()}</td>
                        <td>${item.capital.name}</td>
                        <td>${fn:length(item.cities)}</td>
                        <td>
                            <div class="row">
                                <div class="col-sm-3">
                                    <a class="btn btn-outline-primary btn-sm my-0"
                                       href="/civilizations/${item.id}" role="button">View</a>
                                </div>
                                <div class="col-sm-3">
                                    <form method="post" action="/civilizations/${item.id}" class="mb-0">
                                        <input type="hidden" name="_method" value="DELETE"/>
                                        <button type="submit" class="btn btn-primary btn-sm my-0">Delete
                                        </button>
                                    </form>
<%--                                    <button id="buttonDelete" onclick="deleteCivilization(${item.id})">Delete-js</button>--%>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <form action="/civilizations/form" method="get">
                <button type="submit" class="btn btn-secondary ">Add Civilization</button>
            </form>

        </ul>

        </table>
    </div>
</div>
</body>
</html>

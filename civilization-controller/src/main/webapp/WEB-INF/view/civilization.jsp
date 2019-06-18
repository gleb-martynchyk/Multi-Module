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
<%--    <script src="WEB-INF/view/civilization.js" type="text/javascript"></script>--%>

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
                        <th scope="col">Buildings</th>
                    </tr>
                    <c:forEach items="${civilization.cities}" var="item">
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.level}</td>
                            <td>${item.defence}</td>
                            <td>${item.healthPoint}</td>
                                <%--                            <td>${item.isSieged}</td>--%>
                            <td>
                                <div class="row">
                                    <div class="col-sm-2">
                                            ${fn:length(item.units)}
                                    </div>
<%--                                    <div class="col-sm-2">--%>
<%--                                        <a href="civilizations/${civilization.id}" >+</a>--%>
<%--                                    </div>--%>
                                </div>
                            </td>
                            <td>${item.strength}</td>
                            <td>${item.getFood()}</td>
                            <td>${item.getProduction()}</td>
                            <td>${fn:length(item.improvingBuildings)+fn:length(item.producingBuildings)}
                        </tr>
                    </c:forEach>
                </table>
                <form action="/cities/form" method="get">
                    <button type="submit" class="btn btn-secondary ">Add City</button>
                </form>
            </div>
        </div>

    </div>
    <div class="row mt-5">
        <div class="col-sm">
            <a class="btn btn-primary"
               href="/civilizations" role="button">Civilizations list</a>
        </div>
        <div class="col-sm">
                        <form action="/civilizations/${civilization.id}/nextstep" method="get">
                            <button type="submit" class="btn btn-primary ">Do Tick</button>
                        </form>
<%--            <button id="buttonDelete" onclick="doTickRequest(`/civilizations/${civilization.id}/dotick`)">Do Tick2--%>
<%--            </button>--%>
<%--            <input type="button" id="button"  value="Do step"/>--%>
            <script>
                button.onclick = function () {
                    var xmlHttp = new XMLHttpRequest();
                    xmlHttp.open( "GET", `/civilizations/${civilization.id}/nextstep`, false ); // false for synchronous request
                    xmlHttp.send( null );
                    location.reload();
                };
            </script>
            <%--            <a class="btn btn-primary"--%>
            <%--               href="/civilizations/${civilization.id}/dotick" role="button">Do Tick</a>--%>
        </div>
    </div>

</div>
</body>
</html>

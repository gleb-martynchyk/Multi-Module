<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <h3>Civilizations</h3>
        <br>

        <ul class="list-group">
            <table class="table table-hover table-bordered w-auto">
                <h5>Cities</h5>
                <thead class="thead-light">
                <tr>
                    <th scope="col">#id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Gold</th>
                    <th scope="col">Capital</th>
                    <th scope="col">Cities</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${civilizationsList}" var="item">
                    <td height="50px">
                    <td class="align-middle">${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.gold}</td>
                    <td>${item.capital.name}</td>
                    <td>
                        <a class="btn btn-outline-primary btn-sm my-0"
                           href="civilizations/${item.id}" role="button">View</a>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </ul>

        </table>
    </div>
</div>
</body>
</html>

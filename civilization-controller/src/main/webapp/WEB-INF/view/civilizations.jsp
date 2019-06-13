<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Civilization</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<h4>Civilization</h4>
<br>
<ul class="list-group">
    <c:forEach items="${civilization.cities}" var="item">
        <li class="list-group-item">
            <tr>
                <td><c:out value="${item.name}"/></td>
            </tr>
            <tr>
                <td><c:out value="${item.defence}"/></td>
            </tr>
        </li>
    </c:forEach>
</ul>

</table>
</body>
</html>

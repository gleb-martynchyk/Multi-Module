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
</head>

<body>
<div class="container mt-5">
    <div class="my-auto">

        <h3>Creating Civilization</h3>
        <br>
        <c:if test="${not empty error}">
            <div class="alert alert-warning" role="alert">
                ${error}
            </div>
        </c:if>
        <form:form method="POST"
                   action="/civilizations" modelAttribute="civilization">
            <div class="form-group">
                <form:label path="name">Name</form:label>
                <form:input path="name" type="text" class="form-control" placeholder="Deutschland"/>
            </div>

            <div class="form-group">
                <form:label path="capital.name">Capital Name</form:label>
                <form:input path="capital.name" type="text" class="form-control" placeholder="Berlin"/>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <form:label path="gold" for="inputGold">Gold</form:label>
                    <form:input path="gold" type="number" class="form-control" id="inputGold" placeholder="0"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="faith" for="inputFaith">Faith</form:label>
                    <form:input path="faith" type="number" class="form-control" id="inputFaith" placeholder="0"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="science" for="inputScience">Science</form:label>
                    <form:input path="science" type="number" class="form-control" id="inputScience" placeholder="0"/>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Create</button>
        </form:form>

        <div class="col-sm">
            <a class="btn btn-primary"
               href="/civilizations" role="button">Civilizations list</a>
        </div>
    </div>
</div>
</body>
</html>

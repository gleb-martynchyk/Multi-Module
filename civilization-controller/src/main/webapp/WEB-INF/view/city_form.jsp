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

        <h3>Creating City</h3>
        <br>

        <form:form method="POST"
                   action="/cities" modelAttribute="city">
            <div class="form-group">
                <form:label path="name">Name</form:label>
                <form:input path="name" type="text" class="form-control" id="inputAddress" placeholder="Paradise"/>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <form:label path="defence" for="inputDefence">Defence</form:label>
                    <form:input path="defence" type="number" class="form-control" id="inputGold" placeholder="40"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="healthPoint" for="inputHealthPoint">HealthPoint</form:label>
                    <form:input path="healthPoint" type="number" class="form-control" id="inputHealthPoint" value="100"/>
                </div>
                <div class="form-group col-md-4">
                    <form:label path="strength" for="inputStrength">Strength</form:label>
                    <form:input path="strength" type="number" class="form-control" id="inputStrength" placeholder="0"/>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <form:label path="dominantReligion" for="inputState">Religion</form:label>
                    <select id="inputState" class="form-control">
                        <option selected>None</option>
                        <option>None</option>
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-primary" disabled>Create</button>
        </form:form>

    </div>
</div>
</body>
</html>

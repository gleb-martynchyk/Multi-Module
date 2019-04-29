<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Encryption</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container" align="center">
    <h4>Шифрование текста</h4>
    <div class="row">
        <div class="col">
            <form method="post" action="encryption-servlet">
                <input name="ACTION" value="ENCRYPTION" hidden>
                <div>
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1"><b>Введите исходное сообщение</b></label>
                        <textarea class="form-control" name="input" id="exampleFormControlTextarea1"
                                  rows="3"></textarea>
                    </div>
                </div>
                <div>
                    <br>
                    <input type="submit" value="Зашифровать" class="btn btn-primary">
                </div>
            </form>
            <c:if test="${key != null}">
                <b>Ключ шифрвоания:${key}</b>
                <div class="form-group">
                    <label for="outputEncrypted"><b>Ответ</b></label>
                    <textarea class="form-control" id="outputEncrypted" rows="3">${message}</textarea>
                </div>
            </c:if>
        </div>
        <div class="col">
            <form method="post" action="encryption-servlet">
                <input name="ACTION" value="DECRYPTION" hidden>
                <div>
                    <div class="form-group">
                        <label for="exampleFormControlTextareaEncrypted"><b>Введите зашифрованное сообщение</b></label>
                        <textarea class="form-control" name="inputEncrypted" id="exampleFormControlTextareaEncrypted"
                                  rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Ключ шифрования</label>
                        <input type="number" name="key" class="form-control" id="exampleInputEmail1"
                               aria-describedby="emailHelp" placeholder="Введите ключ">
                    </div>
                </div>
                <div>
                    <br>
                    <input type="submit" value="Расшифровать" class="btn btn-primary">
                </div>
            </form>
            <c:if test="${messageEncrypted != null}">
                <div class="form-group">
                    <label for="outputDecrypted"><b>Ответ</b></label>
                    <textarea class="form-control" id="outputDecrypted" rows="3">${messageEncrypted}</textarea>
                </div>
            </c:if>
        </div>
    </div>
    <c:if test="${errorMessage != null}">
        <b>${errorMessage}</b>
    </c:if>

    <c:choose>
        <c:when test="${area ==null}">
        </c:when>
        <c:when test="${area >0}">
            <div class="row">
                <div class="col">
                    <b>Сообщение:<br/> ${area}</b>
                </div>
                <div class="col">
                    <b>Ключ: ${perimeter}</b>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <b>Неверно введены данные</b>
            <br/>
        </c:otherwise>
    </c:choose>
    <br/>
    <form action="applications.jsp">
        <input type="submit" value="Вернуться к списку задач" class="btn btn-secondary"/>
    </form>
</div>
</body>
</html>

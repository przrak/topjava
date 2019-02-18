<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Users</h2>
<c:if test="${userId eq 0}">
    <p>Вы не выбрали юзера, таблица будет пустой</p>
</c:if>
<c:if test="${userId ne 0}">
    <p>Выбран пользователь с id ${userId}</p>
</c:if>
<p></p><a href="meals">Meals</a></body>
</body>
</html>
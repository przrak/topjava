<%--
  Created by IntelliJ IDEA.
  User: pr1zrak
  Date: 2019-02-10
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        TABLE {
            width: 300px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }

        TD, TH {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 1px solid black; /* Параметры рамки */
        }

        TH {
            background: #b0e0e6; /* Цвет фона */
        }

        .usual {
            color: green;
        }

        .colored {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">На главную</a></h3>
<table>
    <tr>
        <th>Дата\Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th colspan=2>Действия</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr class="${!meal.isExcess() ? 'usual' : 'colored'}">
            <td>${f:formatLocalDateTime(meal.getDateTime())}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td><a href="meals?action=edit&mealId=<c:out value="${meal.getId()}"/>">Редактировать</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meal.getId()}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="meals?action=add">Добавить прием пищи</a></p>
</body>
</html>

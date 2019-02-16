<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %><%--
  Created by IntelliJ IDEA.
  User: pr1zrak
  Date: 2019-02-10
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="f" uri="http://example.com/functions" %>--%>
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

        .normal {
            color: green;
        }

        .exceed {
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
    <c:forEach items="${mealList}" var="meal" >
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.UserMealWithExceed"/>
        <tr class="${meal.exceed ? 'exceed' : 'normal'}">
            <td>
                <%--${f:formatLocalDateTime(userMeal.dateTime)}--%>
                <%--<fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>--%>
                <%--<fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm"/>--%>
                <%=TimeUtil.toString(meal.getDateTime())%>
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="mealList?action=update&id=${meal.id}">Редактировать</a></td>
            <td><a href="mealList?action=delete&id=${meal.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="mealList?action=create">Добавить прием пищи</a></p>
</body>
</html>

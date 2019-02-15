<%--
  Created by IntelliJ IDEA.
  User: pr1zrak
  Date: 2019-02-12
  Time: 00:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new userMeal</title>
</head>
<body>
<h3><a href="index.html">На главную</a></h3>
<c:if test="${userMeal ne null}">
    <h2>Форма для редактирования еды</h2>
</c:if>
<c:if test="${userMeal eq null}">
   <h2>Форма для добавления еды</h2>
</c:if>
<form method="POST" action='meals' name="formAddMeal">
    Описание:
    <input type="text" name="description"
           value="<c:out value="${userMeal.getDescription()}" />"/>
    <br/>
    Калории:
    <input type="number" name="calories"
           value="<c:out value="${userMeal.getCalories()}" />"/>
    <br/>
    Дата/Время:
    <input type="datetime-local" name="datetime"
           value="<c:out value="${userMeal.getDateTime()}"/>"/>
    <br/>
    <input type="hidden" name="id"
           value="<c:out value="${userMeal.getId()}"/>"/>
    <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>


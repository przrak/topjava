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
    <%--<link type="text/css"--%>
    <%--href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />--%>
    <%--<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>--%>
    <%--<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>--%>
    <title>Add new meal</title>
</head>
<body>
<%--<script>--%>
<%--$(function() {--%>
<%--$('input[name=dob]').datepicker();--%>
<%--});--%>
<%--</script>--%>

<form method="POST" action='/topjava/listMeal' name="formAddMeal">
    Описание:
    <input type="text" name="description"
           value="<c:out value="${meal.getDescription()}" />"/>
    <br/>
    Калории:
    <input type="number" name="calories"
           value="<c:out value="${meal.getCalories()}" />"/>
    <br/>
    Дата/Время:
    <input type="datetime-local" name="datetime"
           value="<c:out value="${meal.getDateTime()}"/>"/>
    <br/>
    <input type="hidden" name="id"
           value="<c:out value="${meal.getId()}"/>"/>
    <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>


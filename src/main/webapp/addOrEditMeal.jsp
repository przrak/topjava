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
    <%--User ID : <input type="text" readonly="readonly" name="userid"--%>
    <%--value="<c:out value="${user.userid}" />" /> <br />--%>
    Описание: <input type="text" name="description" value="<c:out value="${meal.getDescription()}" />"/>
    <br/>
    <%--Last Name : <input--%>
    <%--type="text" name="lastName"--%>
    <%--value="<c:out value="${user.lastName}" />"/>--%>
    <%--<br/>--%>
    <%--DOB : <input--%>
    <%--type="text" name="dob"--%>
    <%--value="<fmt:formatDate pattern="MM/dd/yyyy" value="${user.dob}" />"/>--%>
    <%--<br/>--%>
    <%--Email : <input type="text" name="email"--%>
    <%--value="<c:out value="${user.email}" />"/>--%>
    <%--<br/>--%>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>


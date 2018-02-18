<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>

<title>"${title}"</title>

</head>


<body>

<p1>${title}</p1>
<p>

<form:form action="${action}" modelAttribute="book" acceptCharset="utf-8">

<form:hidden path="id" />
<form:hidden path="readAlready" />

<table>
<tr>
    <td><form:label path = "title">Заголовок</form:label></td>
    <td><form:input path = "title" maxlength="100"/></td>
</tr>

<tr>
    <td><form:label path = "description">Описание</form:label></td>
    <td><form:textarea path = "description" raws = "3" maxlength="255" /></td>
</tr>

<tr>
    <td><form:label path = "author">Автор</form:label></td>
    <td><form:input path = "author" maxlength="100" readonly = "${authorReadonly}" /></td>
</tr>

<tr>
    <td><form:label path = "isbn">isbn</form:label></td>
    <td><form:input path = "isbn" maxlength="20"/></td>
</tr>

<tr>
    <td><form:label path = "printYear">Год выпуска</form:label></td>
    <td><form:input path = "printYear" type="number" size="4"/></td>
</tr>


<tr>
    <td colspan = "2">
        <form:button name="set">Сохранить</form:button>
        <form:button name="cansel">Отмена</form:button>
    </td>
</tr>
</table>


</form:form>
</body>
</head>

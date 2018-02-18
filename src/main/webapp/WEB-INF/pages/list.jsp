<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<style type="text/css">
body {
    background-image: url('http://crunchify.com/bg.png');
}
</style>

<title>Полка с кнагами</title>

<script>
    function showDescription(value){
        var targetDiv = document.getElementById('description');
        targetDiv.innerHTML = value;
    }
</script>

</head>

<body>
    <div>
        <br>

        <%@include file = "filterform.jsp" %>
        <table border="1" cellspacing="0">
            <tr>
                <th>Id</th>
                <th>Название</th>
                <th>Автор</th>
                <th>isbn</th>
                <th>Год выпуска</th>
                <th>Уже прочтена</th>
                <th colspan = "2">
                    <a href="<c:url value = '/add'/>">
                    Добавить новую книгу
                    </a>
                </th>
            </tr>
            <c:forEach items = "${bookList}" var = "book">
            </tr>
                <th>${book.id}</th>
                <th>
                    <div
                        style="cursor: pointer;"
                        onclick="showDescription('<c:out value='${book.description}'/>')" >
                        ${book.title}
                    </div>
                </th>
                <th>${book.author}</th>
                <th>${book.isbn}</th>
                <th>${book.printYear}</th>
                <th>
                    <c:choose>
                        <c:when test = "${book.readAlready}">
                            Да
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value = '/read/${book.id}'/>">
                                Нет
                            </a>
                        </c:otherwise>
                    </c:choose>
                </th>
                <th><a href="<c:url value='/update/${book.id}'/>">Новое издание</a></th>
                <th><a href="<c:url value='/delete/${book.id}'/>">Удалить</a></th>
            </tr>
            </c:forEach>
            <tr>
            	<th colspan = "8">
            	    <p>
            	        <div id="description">
            		    </div>
            		</p>
            	</th>
            </tr>
        </table>
        
        
        <br>
        <table> <tr>
        <th>
            <a href="<c:url value='/prev' />"> prev </a>
        </th>
        <th>
            <a href="<c:url value='/next' />"> next </a>
        </th>
        </tr>
        </table>


    </div>
</body>

</html>
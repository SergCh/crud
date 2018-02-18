<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

        <form:form action="setFilter" method="post" modelAttribute="filterForm">
        <table>
        <tr>
            <th align = "left">
                <form:button name="set">Фильтр применить</form:button>
                <form:button name="clear">Фильтр отменить</form:button>
            </th>
        </tr>
        <tr>
            <th align = "left">
                <form:label path="title">Название книги:</form:label>
                <form:input path="title" />
            </th>
        </tr>
        <tr>
            <th align="left">
                <form:label path="readAlready">Выбрать прочитанные и/или не прочитанные</form:label>
                <form:select path="readAlready" items="${readAlreadyValues}" />
            </th>
        </tr>
        <tr>
            <th align="left">
                <form:label path="printYear">Выбрать год</form:label>
                <form:input path="printYear" type="number" size="4" maxlength="4"/>
                <form:select path="beforeAfter" items="${printYearValues}" />
            <th>
        </tr>
        </table>
        </form:form>

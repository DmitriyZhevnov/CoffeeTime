<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title><spring:message code="main.title"/></title>
    <select style="float: right" onchange="window.location.href=this.options[this.selectedIndex].value">
        <option VALUE=""><spring:message code="app.lang.title"/></option>
        <option VALUE="?lang=en"><spring:message code="app.lang.english"/></option>
        <option VALUE="?lang=ru"><spring:message code="app.lang.russian"/></option>
        <option VALUE="?lang=de"><spring:message code="app.lang.deutsch"/></option>
    </select>
</head>
<body>
<div><h2 align="center"><spring:message code="main.title"/></h2></div>
<br/>
<table width="100%" height="80%">
    <tr>
        <td height="10%" valign="top" align="left">
            <form:form action="/newOrder" method="get">
                <input type="submit" value="Принять заказ">
            </form:form>
        </td>
        <td valign="top" align="center">
            <form:form action="/shifts" method="get">
                <input type="submit" value="Учет рабочего времени">
            </form:form>
        </td>
        <td height="10%" valign="top" align="right">
            <form:form action="/main/closeShift" method="get">
                <input type="submit" value="Закрыть смену">
            </form:form>
        </td>
    </tr>
    <tr>
        <td height="10%" valign="top" align="left">
            <form:form action="/closedOrders" method="get">
                <input type="submit" value="Закрытые заказы">
            </form:form>
        </td>
        <td height="10%" valign="top" align="center">
            <form:form action="/menu" method="get">
                <input type="submit" value="Меню">
            </form:form>
        </td>

        <td height="10%" valign="top" align="right">
            <form:form action="/report" method="get">
                <input type="submit" value="Отчёт">
            </form:form>
        </td>
    </tr>
    <tr>
        <td height="70%"></td>
        <td height="70%" valign="top" align="center">
            <form:form action="/warehouse" method="get">
                <input type="submit" value="Склад">
            </form:form>
        </td>
        <td height="70%"></td>
    </tr>
    <tr>
        <td height="10%"></td>
        <td></td>
        <td height="10%" valign="top" align="right">
            <form:form action="/main/block" method="get">
                <input type="submit" value="Заблокировать">
            </form:form>
        </td>
    </tr>
</table>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 09.02.2021
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>orders</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Время</th>
        <th>Итоговая сумма</th>
        <th></th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.time}</td>
            <td>${order.cardAmount + order.cashAmount}</td>
            <td>
                <form action="/closedOrders/${order.id}" method="get">
                    <p><input type="submit" value="Инфо"></p>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/main">Назад</a>
</body>
</html>

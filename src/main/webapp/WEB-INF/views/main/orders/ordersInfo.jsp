<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 09.02.2021
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <td colspan="3">Время: ${order.time}</td>
    </tr>
    <c:forEach items="${order.orderItems}" var="item">
        <tr>
            <td>${item.products.get(0).name}</td>
            <td>${item.quantity} шт.</td>
            <td>${item.products.get(0).price}</td>
        </tr>
    </c:forEach>
    <tr>
        <td>Предытог:</td>
        <td colspan="2">
            <fmt:formatNumber type="number" maxFractionDigits="2" value="${100*(order.cashAmount+order.cardAmount)/(100-order.discount)}"/>
        </td>
    </tr>
    <tr>
        <td>Скидка:</td>
        <td colspan="2">${order.discount}%</td>

    </tr>
    <tr>
        <td>Итого к оплате:</td>
        <td colspan="2">${order.cardAmount + order.cashAmount}</td>
    </tr>
    <tr>
        <td>Тип оплаты:</td>
        <td colspan="2">${order.paymentType}</td>
    </tr>
    <tr>
        <td>Клиент:</td>
        <td>${order.client.name}</td>
        <td>${order.client.phoneNumber}</td>
    </tr>
    <tr>
        <td colspan="3"><a href="/closedOrders">Назад</a></td>
    </tr>
</table>

</body>
</html>

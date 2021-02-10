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
<style>
    * {
        font-family: Areal;
    }

    .b-container {
        /*width: 300px;*/
        /*height: 300px;*/
        /*background-color: #ccc;*/
        /*margin: 0px auto;*/
        /*padding: 10px;*/
        /*font-size: 30px;*/
        color: #000000;
    }

    .b-popup {
        width: 100%;
        min-height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        overflow: hidden;
        position: fixed;
        top: 0px;
    }

    .b-popup .b-popup-content {
        margin: 40px auto 0px auto;
        width: 250px;
        height: 150px;
        padding: 10px;
        background-color: #c5c5c5;
        border-radius: 5px;
        box-shadow: 0px 0px 10px #000;
    }
</style>
<script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
<script>
    $(document).ready(function () {
        //Скрыть PopUp при загрузке страницы
        PopUpHideOrderInfo();
    });

    function PopUpShowOrderInfo() {
        $("#popup1").show();
    }

    function PopUpHideOrderInfo() {
        $("#popup1").hide();
    }
</script>

<head>
    <title>orders</title>
</head>
<body>
<table border="1" width="100%" heigth="100%">
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

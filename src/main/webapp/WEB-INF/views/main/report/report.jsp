<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 10.02.2021
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>report</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Всего</td>
        <td>${report.get(0)}</td>
    </tr>
    <tr>
        <td>Наличные</td>
        <td>${report.get(1)}</td>
    </tr>
    <tr>
        <td>Безналичные</td>
        <td>${report.get(2)}</td>
    </tr>
    <tr>
        <td>Чеков</td>
        <td>${report.get(3)}</td>
    </tr>
    <tr>
        <td>Отменено чеков</td>
        <td>${report.get(4)}</td>
    </tr>
</table>
<a href="/main">Назад</a>
</body>
</html>

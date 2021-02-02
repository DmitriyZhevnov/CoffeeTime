<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 30.01.2021
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>newOrder</title>
</head>
<body>
<table height="100%" width="100%" border="1">
    <tr>
        <td width="30%" valign="top">
            <table border="1" width="100%" heigth="100%">
                <c:forEach items="${basket}" var="item">
                    <tr>
                        <td width="40%">${item.products.get(0).name}</td>
                        <td width="10%">${item.quantity}</td>
                        <td width="40"><a title="add" href="/main/newOrder/add/${item.products.get(0).id}"><img
                                src="<c:url value="/resources/images/addProduct.png"/>" width="20px" height="20px"
                                class="icon"></a>
                            <a title="sub" href="/main/newOrder/sub/${item.products.get(0).id}"><img
                                    src="<c:url value="/resources/images/subProduct.png"/>" width="20px" height="20px"
                                    class="icon"></a>
                            <a title="delete" href="/main/newOrder/delete/${item.products.get(0).id}"><img
                                    src="<c:url value="/resources/images/delProduct.png"/>" width="20px" height="20px"
                                    class="icon"></a>
                        </td>
                        <td width="10">${item.products.get(0).price}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4">
                        <form action='/main/newOrder/makeDiscount' method='post'>
                            <input type="text" name="phoneNumber" value="${phoneNumber}">
                            <input type='submit' value="Применить скидку"/>
                        </form>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">Итого к оплате: ${totalCost}</td>
                </tr>
                <tr>
                    <td colspan="4">
                        оплатить
                    </td>
                </tr>
                <tr>
                    <td colspan="4">Новый пользователь</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <a href="/main">Назад</a>
                    </td>
                </tr>
            </table>
        </td>
        <td valign="top">
            <table width="100%" cellpadding="2" cellspacing="1" border="1">
                <c:forEach items="${coffees}" var="product">
                    <tr>
                        <td>
                            <form action='/main/newOrder/add/${product.id}' method='get'>
                                <input type='submit' value="${product.name}"/>
                            </form>
                        </td>
                        <td>кофе цена</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td valign="top">
            <table width="100%" cellpadding="2" cellspacing="1" border="1">
                <tr>
                    <td>Напитки название</td>
                    <td>напитки цена</td>
                </tr>
            </table>
            <table width="100%" cellpadding="2" cellspacing="1" border="1">
                <tr>
                    <td>Добавки название</td>
                    <td>Добавки цена</td>
                </tr>
            </table>
        </td>
        <td valign="top">
            <table width="100%" cellpadding="2" cellspacing="1" border="1">
                <tr>
                    <td>Батончики название</td>
                    <td>Батончики цена</td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>

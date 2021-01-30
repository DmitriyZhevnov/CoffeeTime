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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>newOrder</title>
</head>
<c:forEach items="${coffees}" var="product">
    <form action='/main/newOrder/add/${product.id}' method='get'>
<%--        <input type="hidden" name="idProduct" value=" ${product.id}"/>--%>
        <input type='submit' value="${product.name}"/>
    </form>
</c:forEach>
<hr/>
<c:forEach items="${basket}" var="item">
    ${item.products.get(0).name} - ${item.quantity}
    <a title="add" href="/main/newOrder/add/${item.products.get(0).id}"><img src="<c:url value="/resources/images/addProduct.png"/>" width="20px" height="20px" class="icon"></a>
    <a title="sub" href="/main/newOrder/sub/${item.products.get(0).id}"><img src="<c:url value="/resources/images/subProduct.png"/>" width="20px" height="20px" class="icon"></a>
    <a title="delete" href="/main/newOrder/delete/${item.products.get(0).id}"><img src="<c:url value="/resources/images/delProduct.png"/>" width="20px" height="20px" class="icon"></a>
    <br/>
</c:forEach>
<form style="alignment: left">

</form>
</body>
</html>

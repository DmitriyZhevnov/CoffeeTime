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
<html>
<head>
    <title>newOrder</title>
</head>
<body>
<c:forEach items="${listOfProducts}" var="product">
    <form action='/shop/' method='POST'>
        <input type="hidden" name="idProduct" value=" ${product.id}"/>
        <input type='submit' value=${product.name}/>
    </form>
</c:forEach>
</body>
</html>

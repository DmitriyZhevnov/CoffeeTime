<%--
  Created by IntelliJ IDEA.
  User: Жевновы
  Date: 13.11.2020
  Time: 00:08
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
    <%--    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
    <%--    <link href="<c:url value="/resources/images/style.css"/>" rel="stylesheet">--%>
    <%--    <p align="right"><a title="English" href="?lang=en"><img src="<c:url value="/resources/images/RU.png"/>" width="30px" height="30px"--%>
    <%--                                                             class="round"></a>--%>
    <%--        <a title="Russian" href="?lang=ru"><img src="<c:url value="/resources/images/UK.jpg"/>" width="30px" height="30px" class="round"></a></p>--%>

    <title><spring:message code="login.title"/></title>

</head>
<body>
<form:form action="/login" method="post" modelAttribute="user">
    <spring:message code="login.login"/>
    <form:input path="login"/>
    <br/>
    <spring:message code="login.password"/>
    <form:input path="password"/>
    <br/>
    <p><input type="submit" value=Войти
        <spring:message code="login.enter"/>>
    </p>
    <p><select size="1" name="comObj">
        <option disabled selected>Выберите ТО</option>
        <c:forEach items="${commercialObjects}" var="commercialobject">
            <option value=${commercialobject.id}>${commercialobject.address}</option>
        </c:forEach>
    </select></p>
</form:form>
</body>
</html>

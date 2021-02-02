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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <option disabled>Выберите ТО</option>
        <c:forEach items="${commercialObjects}" var="commercialobject">
            <option value=${commercialobject.id}>${commercialobject.address}</option>
        </c:forEach>
    </select></p>
</form:form>
</body>
</html>


<%--&lt;%&ndash;/////////////////////////////////////////////&ndash;%&gt;--%>
<%--<style>--%>
<%--    *{--%>
<%--        font-family: Areal;--%>
<%--    }--%>
<%--    .b-container{--%>
<%--        width:200px;--%>
<%--        height:150px;--%>
<%--        background-color: #ccc;--%>
<%--        margin:0px auto;--%>
<%--        padding:10px;--%>
<%--        font-size:30px;--%>
<%--        color: #fff;--%>
<%--    }--%>
<%--    .b-popup{--%>
<%--        width:100%;--%>
<%--        min-height:100%;--%>
<%--        background-color: rgba(0,0,0,0.5);--%>
<%--        overflow:hidden;--%>
<%--        position:fixed;--%>
<%--        top:0px;--%>
<%--    }--%>
<%--    .b-popup .b-popup-content{--%>
<%--        margin:40px auto 0px auto;--%>
<%--        width:100px;--%>
<%--        height: 40px;--%>
<%--        padding:10px;--%>
<%--        background-color: #c5c5c5;--%>
<%--        border-radius:5px;--%>
<%--        box-shadow: 0px 0px 10px #000;--%>
<%--    }--%>
<%--</style>--%>
<%--<script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>--%>
<%--<script>--%>
<%--    $(document).ready(function(){--%>
<%--        //Скрыть PopUp при загрузке страницы--%>
<%--        PopUpHide();--%>
<%--    });--%>
<%--    //Функция отображения PopUp--%>
<%--    function PopUpShow(){--%>
<%--        $("#popup1").show();--%>
<%--    }--%>
<%--    //Функция скрытия PopUp--%>
<%--    function PopUpHide(){--%>
<%--        $("#popup1").hide();--%>
<%--    }--%>
<%--</script>--%>

<%--&lt;%&ndash;//////////////////////////////////////////////////////&ndash;%&gt;--%>
<%--<div class="b-container">--%>
<%--    Sample Text--%>
<%--    <a href="javascript:PopUpShow()">Show popup</a>--%>
<%--</div>--%>
<%--<div class="b-popup" id="popup1">--%>
<%--    <div class="b-popup-content">--%>
<%--        Text in Popup--%>
<%--        <textarea name="toto" cols="40" rows="3"></textarea>--%>
<%--        <a href="javascript:PopUpHide()">Hide popup</a>--%>
<%--        <p><input type="submit" value=Войти--%>
<%--        <spring:message code="login.enter"/>>--%>
<%--        </p>--%>
<%--    </div>--%>
<%--</div>--%>
<%--&lt;%&ndash;////////////////////////////////////////////////////////&ndash;%&gt;--%>
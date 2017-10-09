<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Javair login</title>
</head>
<body>
<div class="container">
    <div class="col-md-12 col-sm-12 col-xs-12 center-text-div">
        <form method="post" class="center-test-div" style="margin: 10px;">
            <div class="input-box">
                <label for="login"><fmt:message key="login.login" /></label>
                <input id="login" type="text" name="login" minlength="5" required><br/>
            </div>
            <div class="input-box">
                <label for="password"><fmt:message key="login.password" /></label>
                <input id="password" type="password" name="password" minlength="5" required><br/>
            </div>
            <c:if test="${not empty redirect}">
                <c:redirect url="${pageContext.request.contextPath}/" />
            </c:if>
            <fmt:message key="submit" var="submit"/>
            <input type="submit" value="${submit}">
            <c:if test="${not empty error}">
                <p style="color: #c9302c">
                    <fmt:message key="${error}" />
                </p>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>

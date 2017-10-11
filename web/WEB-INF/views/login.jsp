<%@ include file="utility/header.jspf" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="row">
    <div class="container">
        <div class="row">
            <div class="col-lg-12" style="text-align: center">
            <span>
            <img src="${pageContext.request.contextPath}/WEB-INF/resources/img/1.png"/>
            </span>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="col-lg-4" style="text-align: center">
                <span>
                <img src="${pageContext.request.contextPath}/WEB-INF/resources/img/1.png"/>
                </span>
                </div>
                <form method="post" class="center-text-div" style="margin: 10px;">
                    <div class="col-lg-4" style="text-align: center">
                        <div class="input-group">
                            <input id="login" type="text" name="login" minlength="5" autofocus required class="form-control"
                                   placeholder="<fmt:message key="login.login" />"
                                   aria-describedby="basic-addon3" value="${login}"><br/>
                            <span class="input-group-addon" id="basic-addon3"><fmt:message key="login"/></span>
                        </div>
                        <br>
                        <div class="input-group">
                            <input id="password" type="password" name="password" minlength="5" required
                                   class="form-control"
                                   placeholder="<fmt:message key="login.password" />"
                                   aria-describedby="basic-addon4"
                                   <%--pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"--%>
                                   title="<fmt:message key="password.hint"/>"><br/>
                            <span class="input-group-addon" id="basic-addon4">
                                <fmt:message key="registration.password"/></span>
                        </div>
                        <br>
                        <br>
                        <c:if test="${not empty redirect}">
                            <c:redirect url="${pageContext.request.contextPath}/javair/flights"/>
                        </c:if>
                        <fmt:message key="submit" var="submit"/>
                        <input type="submit" class="btn btn-info active" value="${submit}">
                        <c:if test="${not empty error}">
                            <p style="color: #c9302c">
                                <fmt:message key="${error}"/>
                            </p>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="utility/footer.jspf" %>
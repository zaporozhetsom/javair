<%@ include file="../utility/header.jspf" %>

<div class="container">
    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="text-align: center">
        <c:if test="${not empty redirect}">
            <c:redirect url="${pageContext.request.contextPath}/"/>
        </c:if>
        <div class="row">
            <div class="col-lg-12" style="text-align: center">
            <span>
            <img src="${pageContext.request.contextPath}/WEB-INF/resources/img/1.png"/>
            </span>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4" style="text-align: center">
            <span>
            <img src="${pageContext.request.contextPath}/WEB-INF/resources/img/1.png"/>
            </span>
            </div>
            <form method="post" class="center-text-div" style="margin: 10px">
                <div class="col-lg-4" style="text-align: center">
                    <div class="input-group">
                        <input class="form-control"
                               placeholder="<fmt:message key="registration.name" />"
                               id="name"
                               type="text"
                               name="name"
                               minlength="3"
                               value="${name}"
                               required
                               autofocus
                               aria-describedby="basic-addon1">
                        <span class="input-group-addon" id="basic-addon1"><fmt:message key="registration.name"/></span>
                    </div>
                    </br>
                    <div class="input-group">
                        <input id="lastname" type="text" name="lastname" minlength="3" required class="form-control"
                               placeholder="<fmt:message key="registration.lastname" />"
                               aria-describedby="basic-addon2"
                               value="${lastname}"><br/>
                        <span class="input-group-addon" id="basic-addon2"><fmt:message
                                key="registration.lastname"/></span>
                    </div>
                    </br>
                    <div class="input-group">
                        <select class="form-control" id="role" name="role" aria-describedby="basic-addon0" required
                                value="${role}">
                            <option value="" disabled selected><fmt:message key="registration.role.select"/></option>
                            <option value="<fmt:message key="registration.role1"/>">
                                <fmt:message key="registration.role1"/>
                            </option>
                            <option value="<fmt:message key="registration.role2"/>">
                                <fmt:message key="registration.role2"/>
                            </option>
                            <option value="<fmt:message key="registration.role3"/>">
                                <fmt:message key="registration.role3"/>
                            </option>
                            <option value="<fmt:message key="registration.role4"/>">
                                <fmt:message key="registration.role4"/>
                            </option>
                            <option value="<fmt:message key="registration.role5"/>">
                                <fmt:message key="registration.role5"/>
                            </option>
                            <option value="<fmt:message key="registration.role6"/>">
                                <fmt:message key="registration.role6"/>
                            </option>
                            <option value="<fmt:message key="registration.role7"/>">
                                <fmt:message key="registration.role7"/>
                            </option>
                            <option value="<fmt:message key="registration.role8"/>">
                                <fmt:message key="registration.role8"/>
                            </option>

                        </select>
                        <span class="input-group-addon" id="basic-addon0"><fmt:message key="registration.role"/></span>
                    </div>


                    </br>
                    <div class="input-group">
                        <input id="login" type="text" name="login" minlength="5" required class="form-control"
                               placeholder="<fmt:message key="registration.login" />"
                               aria-describedby="basic-addon3" value="${login}"><br/>
                        <span class="input-group-addon" id="basic-addon3"><fmt:message key="registration.login"/></span>
                    </div>
                    </br>
                    <div class="input-group">
                        <input id="password1" type="password" name="password1" minlength="5" required
                               class="form-control"
                               placeholder="<fmt:message key="registration.password" />"
                               aria-describedby="basic-addon4" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                               title="<fmt:message key="password.hint"/>"><br/>
                        <span class="input-group-addon" id="basic-addon4"><fmt:message
                                key="registration.password"/></span>
                    </div>
                    </br>
                    <div class="input-group">
                        <input id="password2" type="password" name="password2" minlength="5" required
                               class="form-control"
                               placeholder="<fmt:message key="registration.password.reenter" />"
                               aria-describedby="basic-addon5" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                               title="<fmt:message key="password.hint"/>"><br/>
                        <span class="input-group-addon" id="basic-addon5"><fmt:message
                                key="registration.password.reenter"/></span>
                        <span id='message'></span>
                    </div>
                    </br>
                    <fmt:message key="submit" var="submit"/>
                    <input type="submit" class="btn btn-info active" value="${submit}">
                </div>
            </form>
        </div>
        <c:if test="${not empty error}">
            <p style="color: #c9302c">
                <fmt:message key="${error}"/>
            </p>
        </c:if>
    </div>
</div>

<%@ include file="../utility/footer.jspf" %>
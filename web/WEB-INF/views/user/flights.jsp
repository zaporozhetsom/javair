<%@ include file="../utility/header.jspf" %>
<div class="container col-lg-2 vert-line-right bottom-mar" style="height: 100%;">
    <form method="get" style="margin: 10px">
        <div>
            <div class="row">
                <div class="bottom-mar">
                    <div class="input-group">
                        <select class="form-control" id="model" name="model" aria-describedby="basic-addon0"
                                required
                                value="${model}">
                            <option value="all" <c:if test="${empty model}">selected</c:if>><fmt:message
                                    key="select.option.all"/></option>
                            <c:forEach items="${models}" var="model">
                                <c:forEach items="${manufacturers}" var="manufacturer">
                                    <c:choose>
                                        <c:when test="${manufacturer.id == model.thirdPartyId}">
                                            <c:choose>
                                                <c:when test="${sessionScope.flightFilter.aircraftModel == model.id}">
                                                    <option value="${model.id}"
                                                            selected>${manufacturer.name} ${model.name}</option>
                                                </c:when>

                                                <c:otherwise>
                                                    <option value="${model.id}">${manufacturer.name} ${model.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </c:forEach>
                        </select>
                        <span class="input-group-addon" id="basic-addon0"><fmt:message
                                key="select.title.model"/></span>
                    </div>
                </div>
                <br>
                <div class="bottom-mar">
                    <div class="input-group">
                        <select class="form-control" id="aircraft" name="aircraft" aria-describedby="basic-addon2"
                                required>
                            <option value="all" selected><fmt:message key="select.option.all"/></option>
                            <c:forEach items="${aircrafts}" var="aircraft">
                                <c:choose>
                                    <c:when test="${sessionScope.filter.aircraftRegId == aircraft.registrationIdentifier}">
                                        <option value="${aircraft.id}"
                                                selected>${aircraft.registrationIdentifier} </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${aircraft.id}">${aircraft.registrationIdentifier}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <span class="input-group-addon" id="basic-addon2"><fmt:message
                                key="select.title.aircraft"/></span>
                    </div>
                </div>
                <br>
                <div class="bottom-mar">
                    <div class="input-group">
                        <select class="form-control" id="airport" name="airport" aria-describedby="basic-addon3"
                                required>
                            <option value="all" selected><fmt:message key="select.option.all"/></option>
                            <c:forEach items="${airports}" var="airport">
                                <c:choose>
                                    <c:when test="${sessionScope.filter.airport == airport.id}">
                                        <option value="${airport.id}" selected>${airport.IATACode}(${airport.city})
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${airport.id}">${airport.IATACode}(${airport.city})</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <span class="input-group-addon" id="basic-addon3">
                            <fmt:message key="select.title.airport"/></span>
                    </div>
                </div>
            </div>
            <br>
            <fmt:message key="submit" var="submit"/>
            <input type="submit" class="btn btn-info active" value="${submit}">
        </div>
    </form>
</div>
<c:if test="${empty flights}">
    <div class="container col-lg-10">
        <div class="panel panel-info">
            <div class="panel-heading"><fmt:message key="empty.list.header"/></div>
            <div class="panel-body">
                <fmt:message key="empty.list"/>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${not empty flights}">
    <div class="container col-lg-offset-2">

    <c:forEach items="${flights}" var="flight">
        <c:choose>
            <c:when test="${flight.flightType != 'SCHEDULED'}">
                <c:choose>
                    <c:when test="${flight.flightType != 'ARCHIVE'}">
                        <div class="col-lg-offset-1">
                        <div class="panel panel-success">
                    </c:when>
                    <c:otherwise>
                        <div class="col-lg-offset-1">
                        <div class="panel panel-danger">
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <div class="col-lg-offset-1">
                <div class="panel panel-primary">
            </c:otherwise>
        </c:choose>


        <div class="panel-heading"><fmt:message key="flight.type"/>: ${flight.flightType}</div>
        <div class="panel-body">
            <div class="col-lg-2">
                <div class="well">
                    <h5>Flight Id:</h5> <h5>${flight.id}</h5></div>
                <div class="well">
                    <h5>Aircraft Id:</h5>
                    <h4>${flight.aircraftId}</h4></div>


                <c:forEach items="${aircrafts}" var="aircraft">
                    <c:forEach items="${models}" var="model">
                        <c:forEach items="${manufacturers}" var="manufacturer">
                            <c:choose>
                                <c:when test="${aircraft.registrationIdentifier == flight.aircraftId}">
                                    <c:choose>
                                        <c:when test="${aircraft.model == model.name}">
                                            <c:choose>
                                                <c:when test="${model.thirdPartyId == manufacturer.id}">
                                                    <div class="well">
                                                        <h5>Aircraft model:</h5>
                                                        <h4>${manufacturer.name} ${model.name}</div>
                                                    </h4>
                                                </c:when>
                                            </c:choose>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </c:forEach>
                </c:forEach>
            </div>


            <div class="col-lg-4">
                <div class="panel panel-info">
                    <div class="panel-heading"><fmt:message key="departure"/></div>
                    <div class="panel-body">
                        <div class="well">
                            <h4>
                                <c:forEach items="${airports}" var="airport">
                                    <c:choose>
                                        <c:when test="${airport.baseAirport}">
                                            ${airport.city}(${airport.IATACode})
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </h4>
                            <h5>${flight.forwardDepartureDateTime}</h5>
                        </div>
                        <div class="well">
                            <h4>
                                <c:forEach items="${airports}" var="airport">
                                    <c:choose>
                                        <c:when test="${airport.id == flight.destinationAirportId}">
                                            ${airport.city}(${airport.IATACode})
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </h4>
                            <h5>${flight.backwardDepartureDateTime}</h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="panel panel-info">
                    <div class="panel-heading"><fmt:message key="arrival"/></div>
                    <div class="panel-body">
                        <div class="well">
                            <h4>
                                <c:forEach items="${airports}" var="airport">
                                    <c:choose>
                                        <c:when test="${airport.id == flight.destinationAirportId}">
                                            ${airport.city}(${airport.IATACode})
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </h4>
                            <h5>${flight.forwardArrivalDateTime}</h5>
                        </div>
                        <div class="well">
                            <h4>
                                <c:forEach items="${airports}" var="airport">
                                    <c:choose>
                                        <c:when test="${airport.baseAirport}">
                                            ${airport.city}(${airport.IATACode})
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </h4>
                            <h5>${flight.backwardArrivalDateTime}</h5>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        </div>
        </div>
    </c:forEach>
    </div>

    <%--<c:choose>--%>
        <%--<c:when test="${lastPage > 1}">--%>
            <%--<div class="col-lg-12">--%>
                <%--<nav aria-label="Page navigation">--%>
                    <%--<ul class="pagination">--%>
                        <%--<li>--%>
                            <%--<a href="#" aria-label="Previous">--%>
                                <%--<span aria-hidden="true">&laquo;</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                        <%--<c:forEach var="i" begin="1" end="${lastPage}">--%>
                            <%--<li><a href="#">${i}</a></li>--%>
                        <%--</c:forEach>--%>
                        <%--<li>--%>
                            <%--<a href="#" aria-label="Next">--%>
                                <%--<span aria-hidden="true">&raquo;</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</nav>--%>
            <%--</div>--%>
        <%--</c:when>--%>
    <%--</c:choose>--%>
    <div class="col-lg-12">
        <c:forEach begin="1" end="${lastPage}" var="i">
            <c:choose>
                <c:when test="${i eq currentPage}">
                    <b>${i}</b>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/javair/flights/?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>

</c:if>
<%@ include file="../utility/footer.jspf" %>
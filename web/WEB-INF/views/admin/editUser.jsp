<%@ include file="../utility/header.jspf"%>

<div class="container-fluid">
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
        .tg .tg-yw4l{vertical-align:top; text-align: center}
    </style>
    <c:if test="${not empty users}">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <table class="tg">
                <tr>

                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <th class="tg-yw4l">${user.id}</th>
                        <th class="tg-yw4l">${user.login}</th>
                        <th class="tg-yw4l">${user.firstName}</th>
                        <th class="tg-yw4l">${user.lastName}</th>
                        <th class="tg-yw4l">${user.role}</th>
                        <c:choose>
                            <c:when test="${user.role == 'ADMIN'}">
                                <th class="tg-yw4l"> --- </th>
                            </c:when>
                            <c:otherwise>
                                <th class="tg-yw4l">
                                    <form method="post">
                                        <input type="hidden" value="${user.id}" name="userId">

                                        <input type="submit" value="${setAdmin}">
                                    </form>
                                </th>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>

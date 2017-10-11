<%@ include file="../utility/header.jspf" %>
<div class="container">
    <div class="row">
        
        <div class="col-md-12 col-sm-12 col-xs-12 center-text">
            <c:forEach begin="1" end="${lastPage}" var="i">
                <c:choose>
                    <c:when test="${i eq currentPage}">
                        <b>${i}</b>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
</div>
<%@ include file="../utility/footer.jspf" %>
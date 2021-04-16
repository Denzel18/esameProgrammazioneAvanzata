<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!--<sec:authorize access="isAuthenticated()" var="isAuth"/>-->
<c:url value="/manutenzione/new" var="newManutenzione_url"/>
<div class="col-md-12 mb-4">
    <div class="row">
        <c:if test="${fn:length(successMessage) > 0}">
            <div class="alert alert-success mx-auto" role="alert">${successMessage}
                <button type="button" class="close ml-3" data-dismiss="alert" aria-label="Close" style="font-size: 1.4rem;">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <c:if test="${fn:length(errorMessage) > 0}">
            <div class="alert alert-danger mx-auto" role="alert">${errorMessage}
                <button type="button" class="close ml-3" data-dismiss="alert" aria-label="Close" style="font-size: 1.4rem;">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
    </div>
        <div class="overflow-auto">
            <h5 class="text-center font-weight-bold mt-4 mb-4">Lista delle manutenzioni</h5>
	<div class="font-weight-bold text-center">Numero manutenzioni: ${numManutenzioni}</div>
		<table class="table table-striped w-75 mx-auto">
                <thead>
                <tr>
                    <th scope="col">Tipo Manutenzione</th>
                    <th scope="col">Costo Manutenzione</th>
                    <th scope="col">Cars</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${manutenzioni}" var="manutenzione">
                    <tr>
                        <td>${manutenzione.tipoManutenzione}</td>
                        <td>${manutenzione.costoManutenzione}</td>
                        <td>${manutenzione.veicolo.targa}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

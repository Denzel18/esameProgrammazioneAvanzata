<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<!-- <sec:authorize access="isAuthenticated()" var="isAuth"/> -->
<script type="text/javascript">
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
    })
</script>
<c:url value="/documentazione/new" var="newDocumentazione_url"/>
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
        <h5 class="text-center font-weight-bold mt-4 mb-4">Lista di tutti i documenti</h5>
        <div class="font-weight-bold text-center">Numero documenti: ${numDocumentazioni}</div>
        <table class="table table-striped w-75 mx-auto">
            <thead>
            <tr>
                <th scope="col">Titolo</th>
                <th scope="col">Descrizione</th>
                <th scope="col">Utente</th>
                <th scope="col">Veicolo</th>
                <th scope="col">Data Scadenza</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${documentazioni}" var="doc">
                <tr>
                    <td>${doc.titolo }</td>
                    <td>${doc.descrizione }</td>
                    <td>${doc.getUtente().username }</td>
                    <td>${doc.getVeicolo().targa }</td>
                    <td>${doc.dataScadenza }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

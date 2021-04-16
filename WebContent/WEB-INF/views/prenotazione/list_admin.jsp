<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- <sec:authorize access="isAuthenticated()" var="isAuth"/> -->
<c:url value="/prenotazione/new" var="newPrenotazione_url"/>

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
            <h5 class="text-center font-weight-bold mt-4 mb-4">Lista di tutte le prenotazioni </h5>
	<div class="font-weight-bold text-center">Numero prenotazioni: ${numPrenotazioni}</div>
		<table class="table table-striped w-75 mx-auto">
                <thead>
                <tr>
                    <th scope="col">Data Inizio</th>
                    <th scope="col">Data Fine</th>
                    <th scope="col">Ora Inizio</th>
                    <th scope="col">Ora Fine</th>
                    <th scope="col">Descrizione</th>
                    <th scope="col">Utente</th>
                    <th scope="col">Veicolo</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${prenotazioni}" var="prenotazione">
                    <tr>
                        <td>${prenotazione.dataInizio}</td>
                        <td>${prenotazione.dataFine}</td>
                        <td>${prenotazione.oraInizio}</td>
                        <td>${prenotazione.oraFine}</td>
                        <td>${prenotazione.descrizione}</td>
                        <td>${prenotazione.utente.username}</td>
                        <td>${prenotazione.veicolo.targa}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

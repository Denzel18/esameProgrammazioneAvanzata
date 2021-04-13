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
                    <th scope="col">Id</th>
                    <th scope="col">Data Inizio</th>
                    <th scope="col">Data Fine</th>
                    <th scope="col">Ora Inizio</th>
                    <th scope="col">Ora Fine</th>
                    <th scope="col">Descrizione</th>
                    <th scope="col">Utente_ID</th>
                    <th scope="col">Veicolo_ID</th>
                    <th scope="col">Visualizza</th>
                    <th scope="col">Modifica</th>
                    <th scope="col">Elimina</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${prenotazioni}" var="prenotazione">
                    <tr>
                        <td>${prenotazione.id}</td>
                        <td>${prenotazione.dataInizio}</td>
                        <td>${prenotazione.dataFine}</td>
                        <td>${prenotazione.oraInizio}</td>
                        <td>${prenotazione.oraFine}</td>
                        <td>${prenotazione.descrizione}</td>
                        <td>${prenotazione.utente.username}</td>
                        <td>${prenotazione.veicolo.id}</td>
                        <td><a class="btn btn-info" href="<c:url value="/prenotazione/${prenotazione.id}"/>"><i class="fa fa-info"></i></a></td>
                        <td><a class="btn btn-info" href="<c:url value="/prenotazione/edit/${prenotazione.id}"/>"><i class="fa fa-edit"></i></a></td>
                        <td><a class="btn btn-danger"
                            href="<c:url value="/prenotazione/delete/${prenotazione.id}"/>"/
                            title="Elimina Prenotazione"
                            onclick='return confirm("Sei sicuro di voler eliminare la prenotazione : \"${prenotazione.id}\"?");'>
                             <i class="fa fa-trash"></i></a></td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="text-center mb-4">
            <a type="button" class="btn btn-success" href="${newPrenotazione_url}">Aggiungi un nuovo prenotazione</a>
   	    </div>
    </div>

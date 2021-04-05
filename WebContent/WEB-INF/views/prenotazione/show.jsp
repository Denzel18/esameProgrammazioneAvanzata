<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="col">
<table class="table table-striped w-75 mx-auto">
    <thead>
        <th scope="col">Id</th>
        <th colspan="2" scope="col">Data Inizio</th>
        <th colspan="2" scope="col">Data Fine</th>
        <th colspan="2" scope="col">Ora Inizio</th>
        <th colspan="2" scope="col">Ora Fine</th>
    </thead>
    <tr>
        <td>${prenotazione.id}</td>
        <td colspan="2" >${prenotazione.dataInizio}</td>
        <td colspan="2" >${prenotazione.dataFine}</td>
        <td colspan="2" >${prenotazione.oraInizio}</td>
        <td colspan="2" >${prenotazione.oraFine}</td>
    </tr>
    <thead>
        <th scope="col" colspan="2">Descrizione</th>
        <th scope="col">Utente_ID</th>
        <th scope="col">Veicolo_ID</th>
        <th colspan="3" scope="col">Elimina</th>
    </thead>
    <tr>
        <td colspan="2">${prenotazione.descrizione}</td>
        <td>${prenotazione.utente.username}</td>
        <td>${prenotazione.veicolo.targa}</td>
        <td colspan="3"><a class="btn btn-danger"
            href="<c:url value="/prenotazione/delete/${prenotazione.id}"/>"/
            title="Elimina Prenotazione"
            onclick='return confirm("Sei sicuro di voler eliminare la prenotazione : \"${prenotazione.id}\"?");'>
             <i class="fa fa-trash"></i></a>
        </td>
    </tr>

</table> 
</div>

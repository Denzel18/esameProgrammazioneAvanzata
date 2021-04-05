<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/prenotazione/edit/save/" var="action_url"/>
<div class="col">
    <%--@elvariable id="prenotazione" type="it.univpm.advancedcode.cri.model.entities.Prenotazione"--%>
    <hr>
    <form:form name="modulo" action="${action_url}" method="POST" modelAttribute="prenotazione" enctype="multipart/form-data">
        <h1 class="h3 mb-3 font-weight-normals">Modifica prenotazione</h1>
        <p class="text-danger">TUTTI I CAMPI SONO OBBLIGATORI</p>
        <form:hidden value="${prenotazione.id}" path="id" class="form-control mt-2"/>

        <form:label path="dataInizio">Data Inizio</form:label>
        <form:input value="${prenotazione.dataInizio}" type="date" path="dataInizio" class="form-control mt-2" />

        <form:label path="dataFine">Data Fine</form:label>
        <form:input value="${prenotazione.dataFine}" type="date" path="dataFine" class="form-control mt-2" />

        <form:label path="oraInizio">Ora Inizio</form:label>
        <form:input value="${prenotazione.oraInizio}" type="time" path="oraInizio" class="form-control mt-2" onclick="controlloData()"/>

        <form:label path="oraFine">Ora Fine</form:label>
        <form:input value="${prenotazione.oraFine}" type="time" path="oraFine" class="form-control mt-2" />

        <form:label path="descrizione">Descrizione</form:label>
        <form:input value="${prenotazione.descrizione}" path="descrizione" class="form-control mt-2" onclick="controlloOra()"/>

        <form:label path="utente">Utente(username)</form:label>
        <form:input value="${prenotazione.utente.username}" path="utente" class="form-control mt-2" />

        <form:label path="veicolo">Veicolo</form:label>
        <form:input value="${prenotazione.veicolo.id}" path="veicolo" class="form-control mt-2" />

        <div class="d-flex justify-content-center">
            <input onclick="controlloAltriCampi()" type="submit" value="Modifica"
                class="mt-3 btn btn-primary col-4" />
        </div>
    </form:form>
    <hr>
</div>


<script type="text/javascript">
    //Se vengono superati i controlli abilita l'inoltro del form (di default disabilitato)
    function controlloData() {

        var dataInizio = document.getElementById("dataInizio").value; 
        var dataFine= document.getElementById("dataFine").value;   
        var data_dataInizio = new Date(dataInizio); 
        var data_dataFine = new Date(dataFine); 

        if(data_dataInizio > data_dataFine)
            alert("Date non correte!");
    }

    function controlloOra() {
        var oraInizio = document.getElementById("oraInizio").value; 
        var oraFine= document.getElementById("oraFine").value;   
        var ora_oraInizio = new Time(oraInizio); 
        var ora_oraFine = new Time(oraFine); 

        if(ora_oraInizio > ora_oraFine)
            alert("Ore non correte!");
    }
    function controlloAltriCampi(){
        var username = document.getElementById("descrizione").value ; 
        var utente = document.getElementById("utente").value ; 
        var veicolo = document.getElementById("veicolo").value ; 

        if(username == "" || utente == "" || veicolo == "")
            alert("Campi obbligatori non inseriti!");
        else
            document.modulo.submit();
    }
</script>
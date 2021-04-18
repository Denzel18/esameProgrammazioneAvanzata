<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/prenotazione/new/save/" var="action_url"/>

<div class="col">

    <form:form name="modulo" class="form-signin" action="${action_url}" method="POST" modelAttribute="prenotazione">
        <h1 class="h3 mb-3 font-weight-normals">Inserisci prenotazione</h1>
        <%--@elvariable id="prenotazione" type="it.univpm.advancedcode.cri.model.entities.Prenotazione"--%>
        	<p class="text-danger">TUTTI I CAMPI SONO OBBLIGATORI</p>
            
            <form:label path="dataInizio">Data Inizio(DD/MM/YY)*</form:label>
			<form:input path="dataInizio" type="text" name="dataInizio" value="00/00/00" class="form-control mt-2" />

            <form:label path="dataFine">Data Fine(DD/MM/YY)*</form:label>
			<form:input path="dataFine" type="text" name="dataFine" value="00/00/00" class="form-control mt-2" />

            <form:label path="oraInizio">Ora Inizio(HH:MM)*</form:label>
			<form:input path="oraInizio" type="text" name="oraInizio" value="00:00" class="form-control mt-2" />

            <form:label path="oraFine">Ora Fine(HH:MM)*</form:label>
			<form:input  path="oraFine" type="text" name="oraFine" value="00:00" class="form-control mt-2" />

            <form:label path="descrizione">Descrizione</form:label>
			<form:input path="descrizione" type="text" name="descrizione" value="Descrizione..." class="form-control mt-2" />

            <form:label path="utente">Utente*</form:label>
            <form:input path="utente" type="text" name="utente"  value ="${user}" class="form-control mt-2" readonly="true" />
	        
            <form:label name="veicolo" path="veicolo">VEICOLO(TARGA)</form:label>
            <form:select name="veicolo" path="veicolo" class="form-control mt-2" >
                <c:forEach items="${allCars}" var="car">
                    <option value="${car.id}">${car.targa}</option>
                </c:forEach>
            </form:select>
					
			<div class="d-flex justify-content-center">
				<input class="btn btn-primary col-3" type="submit" value="Inserisci" onclick="controlloAltriCampi()"/>
			</div>
        </div>
    </form:form>
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
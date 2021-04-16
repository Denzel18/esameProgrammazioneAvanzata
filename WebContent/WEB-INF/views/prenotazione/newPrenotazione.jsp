<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/prenotazione/new/save/" var="action_url"/>

<div class="card mb-5">
    <form:form name="modulo" class="form-signin" action="${action_url}" method="POST" modelAttribute="user" enctype="multipart/form-data">
       		<h1 class="h3 mb-3 font-weight-normal">Inserisci prenotazione</h1>
       	</div>
        
        <div class="card-body">
        	<p class="text-danger">* Campo obbligatorio.</p>
	        <div class="form-group">
	        	<label>Data Inizio(DD/MM/YY)*</label>
	        	<input type="text" name="dataInizio" value="00/00/00" class="form-control mt-2" />
	        </div>
            <div class="form-group">
	        	<label>Data Fine(DD/MM/YY)*</label>
	        	<input type="text" name="dataFine" value="00/00/00" class="form-control mt-2" />
	        </div>
            <div class="form-group">
	        	<label>Ora Inizio(HH:MM)*</label>
	        	<input type="text" name="oraInizio" value="00:00" class="form-control mt-2" onclick="controlloData()" />
	        </div>
            <div class="form-group">
	        	<label>Ora Fine(HH:MM)*</label>
	        	<input type="text" name="oraFine" value="00:00" class="form-control mt-2" />
	        </div>
			<div class="form-group">
	        	<label>Descrizione*</label>
	        	<input type="text" name="descrizione" value="desc..." class="form-control mt-2" onclick="controlloOra()"/>
	        </div>

			<div class="form-group">
				<label>Utente*</label>
		        <input type="text" name="utente"  value ="${user}" class="form-control mt-2" disabled="disabled"/>
				<p id=name_err></p>
			</div>
	        
			<div class="form-group">
		        <label>Veicolo*</label>
                <select name="veicolo">
                    <c:forEach items="${allCars}" var="car">
                        <option value="${car.id}">${car.targa}</option>
                    </c:forEach>
                </select>
			</div>
					
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
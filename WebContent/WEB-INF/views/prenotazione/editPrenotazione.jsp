<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/prenotazione/edit/save/" var="action_url"/>

<div class="card mb-5">
    <form:form name="modulo" class="form-signin" action="${action_url}" method="POST" modelAttribute="prenotazione" enctype="multipart/form-data">
       		<h1 class="h3 mb-3 font-weight-normal">Inserisci prenotazione</h1>
       	</div>
        <form:hidden value="${prenotazione.id}" path="id" class="form-control mt-2"/>


        <div class="card-body">
        	<p class="text-danger">* Campo obbligatorio.</p>
	        <div class="form-group">
	        	<label>Data Inizio*</label>
                <input type="text" id="dataInizio" name="dataInizio" value="${prenotazione.dataInizio}" class="form-control mt-2" onclick="data_inizio()" />
	        </div>
            <div class="form-group">
	        	<label>Data Fine*</label>
	        	<input type="text" id="dataFine" name="dataFine" value="${prenotazione.dataFine}" class="form-control mt-2" onclick="data_fine()"/>
	        </div>
            <div class="form-group">
	        	<label>Ora Inizio*</label>
	        	<input type="text" id="oraInizio" name="oraInizio" value="${prenotazione.oraInizio}" class="form-control mt-2" onclick="controlloData()" />
	        </div>
            <div class="form-group">
	        	<label>Ora Fine*</label>
	        	<input type="text" id="oraFine" name="oraFine" value="${prenotazione.oraFine}" class="form-control mt-2" />
	        </div>
			<div class="form-group">
	        	<label>Descrizione*</label>
	        	<input type="text" name="descrizione" value="${prenotazione.descrizione}" class="form-control mt-2" onclick="controlloOra()"/>
	        </div>

			<div class="form-group">
				<label>Utente*</label>
		        <input type="text" name="utente"  value="${prenotazione.utente.username}" class="form-control mt-2" readonly="readonly"/>
			</div>
	        
			<div class="form-group">
		        <label>Veicolo*</label>
                <input type="text" name="veicolo"  value="${prenotazione.veicolo.id}" class="form-control mt-2" readonly="readonly"/>
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
        data_fine();
        data_inizio();

        if(username == "" || utente == "" || veicolo == "")
            alert("Campi obbligatori non inseriti!");
        else
            document.modulo.submit();
    }

    function data_inizio(){
        var dataInizio = document.getElementById("dataInizio").value ; 
        var data_inizio = dataInizio.split('-');
        console.log('year : ', data_inizio[0]);
        console.log('month: ', data_inizio[1]);
        console.log('day: ', data_inizio[2]);
        var_data_format = data_inizio[2]+'/'+data_inizio[1]+'/'+(data_inizio[0]%1000);
        var dataInizio = document.getElementById("dataInizio").value = var_data_format ; 
    }

    function data_fine(){
        var dataFine = document.getElementById("dataFine").value ; 
        var data_fine = dataFine.split('-');
        console.log('year : ', data_fine[0]);
        console.log('month: ', data_fine[1]);
        console.log('day: ', data_fine[2]);
        var_data_format = data_fine[2]+'/'+data_fine[1]+'/'+(data_fine[0]%1000);
        var dataInizio = document.getElementById("dataFine").value = var_data_format ; 
    }
</script>
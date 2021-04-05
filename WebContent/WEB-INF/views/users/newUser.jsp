<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/user/new/save" var="action_url"/>
<div class="card mb-5">
    <form:form name="modulo" class="form-signin" action="${action_url}" method="POST" modelAttribute="user" enctype="multipart/form-data">
       		<h1 class="h3 mb-3 font-weight-normal">Inserisci i tuoi dati</h1>
       	</div>
        
        <div class="card-body">
        	<p class="text-danger">* Campo obbligatorio.</p>
	        <div class="form-group">
	        	<label>Username*</label>
	        	<input type="text" name="username" value="username" class="form-control mt-2" />
	        </div>
			<div class="form-group">
	        	<label>Password*</label>
	        	<input type="text" name="password" value="password" class="form-control mt-2" />
	        </div>

			<div class="form-group">
				<label>Nome*</label>
		        <input type="text" name="firstname"  value ="Mario" class="form-control mt-2" onblur = "controlloNome()" autofocus/>
				<p id=name_err></p>
			</div>
	        
			<div class="form-group">
		        <label>Cognome*</label>
		        <input type="text" name="lastname"  value="Rossi" class="form-control mt-2"  onblur = "controlloCognome()"/>
				<p id=lastname_err></p>
			</div>

			<div class="form-group">
		        <label>Ruolo</label>
				<select id="cars" name="ruolo">
					<option value="admin">Admin</option>
					<option value="driver">Driver</option>
					<option value="account">Account</option>
				  </select>
			</div>
			
			<div class="form-group">
		        <label>Immagine Profilo</label>
		        <input type="file" name="image" class="form-control mt-2"/>
		        <small><b>MAX 1MB</b></small>
			</div>			
			<div class="d-flex justify-content-center">
				<input class="btn btn-primary col-3" type="submit" value="Inserisci" onclick="controlloForm()"/>
			</div>
	        <form:hidden path="imageProfile"/>
        </div>
    </form:form>
</div>

<script type="text/javascript">
	function controlloNome() {
		document.modulo.firstName.removeAttribute("style");
		document.getElementById("name_err").innerHTML = "";
		var pattern = /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?)+$/;
		if (!pattern.test(document.modulo.firstName.value)) {
			document.modulo.firstName.style.backgroundColor = "#FFB6C1";
			document.modulo.firstName.style.borderColor = "red";
			var msg = document.getElementById("name_err");
			msg.innerHTML = "Nome non valido!";
			msg.style.color = "red";			
		}
	}
	 
	 function controlloCognome() {
		 document.modulo.lastName.removeAttribute("style");
		 document.getElementById("lastname_err").innerHTML = "";
		 var pattern = /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?)+$/;
		 if (!pattern.test(document.modulo.lastName.value)) {
			 	document.modulo.lastName.style.backgroundColor = "#FFB6C1";
				document.modulo.lastName.style.borderColor = "red";
				var msg = document.getElementById("lastname_err");
				msg.innerHTML = "Cognome non valido!";
				msg.style.color = "red";
				
		 }
		 
	 }
	
		//Se vengono superati i controlli abilita l'inoltro del form (di default disabilitato)
		function controlloForm() {	
			var nome = document.modulo.firstName.value;
			var cognome = document.modulo.lastName.value;
			var pattern_names = /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?)+$/; //no numeri
			
			if ( (!pattern_names.test(nome)) || (!pattern_names.test(cognome)) ) {
				alert("Dati inseriti non validi");
			}
			else document.modulo.submit();
		}
</script>
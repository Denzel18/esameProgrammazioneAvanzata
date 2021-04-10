<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/documentazione/save" var="action_url" />


<!-- 	private long id;
	private String titolo;
	private User utente;
	private String descrizione;
	private Date dataScadenza; 
	private float costo; 
	 -->
<div class="row justify-content-center">
	<div class="col-8">
		<%--@elvariable id="documento" type="it.univpm.advancedcode.cri.model.entities.Documentazione"--%>
		<form:form name="modulo" class="form-signin" action="${action_url}" method="POST" modelAttribute="documentazione" onsubmit="return false">
			<div class="text-center mb-3">
				<h3 class="font-weight-bold">"Inserimento nuovo documento"</h3>
			</div>
			<p class="text-danger">Tutti i campi sono obbligatori</p>

			<form:label path="titolo">Titolo</form:label>
			<form:input id="titolo" path="titolo" class="form-control mt-2" />
			<br>

			<form:label path="descrizione">Descrizione</form:label>
			<form:input id="descrizione" path="descrizione"
				class="form-control mt-2" />
			<br>

			<form:label path="dataScadenza">Data Scadenza</form:label>
			<form:input id="dataScadenza" path="dataScadenza"
				class="form-control mt-2" />
			<br>

			<form:label path="costo">Costo</form:label>
			<form:textarea id="costo" path="costo" class="form-control mt-2"
				style="resize:none" rows="4" />
			<br>
 
			<label>Seleziona il/i veicolo <select name="veicolo_id"
				multiple class="custom-select">
					<c:choose>
						<c:when test="${not empty carsDocumentazione}">
							<c:forEach items="${allCars}" var="car">
								<c:choose>
									<c:when test="${carsDocumentazione.contains(car.targa)}">
										<option value="${car.id}" selected>${car.targa}</option>
									</c:when>
									<c:otherwise>
										<option value="${car.id}">${car.targa}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${allCars}" var="car">
								<option value="${car.id}">${car.targa}</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
			</select><br>
			</label> 
			<label>Seleziona utente <select name="username"
				multiple class="custom-select">
					<c:choose>
						<c:when test="${not empty allUsers}">
							<c:forEach items="${allUsers}" var="user">
								<c:choose>
									<c:when test="${allUsers.contains(user.username)}">
										<option value="${user.username}" selected>${user.username}</option>
									</c:when>
									<c:otherwise>
										<option value="${user.username}">${user.username}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${allUsers}" var="user">
								<option value="${user.username}">${user.username}</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
			</select><br>
			</label> 


			<form:hidden path="id" />

			<input type="submit" value="Submit"
				class="mt-3 btn btn-lg btn-primary btn-block"
				onclick="controlloForm()" />
			<br>
			<br>
		</form:form>
	</div>
</div>


<script type="text/javascript">


//Se vengono superati i controlli abilita l'inoltro del form (di default disabilitato)
function controlloForm() {
	
	var title = document.getElementById("titolo").value;
	if (title.length === 0) {
		alert("Documento incompleto!");
	}
	else document.modulo.submit();
}


</script>


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
		<form:form name="modulo" class="form-signin" action="${action_url}"
			method="POST" modelAttribute="car" onsubmit="return false">
			<div class="text-center mb-3">
				<h3 class="font-weight-bold">"Inserimento nuovo documento"}</h3>
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

			<label>Seleziona il/i veicolo <select name="carsSelected"
				multiple class="custom-select">
					<c:choose>
						<c:when test="${not empty carsDocumentazione}">
							<c:forEach items="${allCars}" var="car">
								<c:choose>
									<c:when test="${carsDocumentazione.contains(car.targa)}">
										<option value="${car.targa}" selected>${car.targa}</option>
									</c:when>
									<c:otherwise>
										<option value="${car.targa}">${car.targa}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${allCars}" var="tag">
								<option value="${car.targa}">${car.targa}</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
			</select><br>
			</label>

			<label>Seleziona utente <select name="userSelected"
				multiple class="custom-select">
					<c:choose>
						<c:when test="${not empty carsUtente}">
							<c:forEach items="${allusers}" var="user">
								<c:choose>
									<c:when test="${carsUtente.contains(user.username)}">
										<option value="${user.username}" selected>${user.username}</option>
									</c:when>
									<c:otherwise>
										<option value="${user.username}">${user.username}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${allusers}" var="tag">
								<option value="${user.username}">${user.username}</option>
							</c:forEach>
						</c:otherwise>
					</c:choose>
			</select><br>
			</label>


			<form:hidden path="id" />
			<form:hidden path="hide" />


			<form:hidden path="archive.name" />
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

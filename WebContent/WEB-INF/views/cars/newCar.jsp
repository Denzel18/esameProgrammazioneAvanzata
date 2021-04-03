<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/car/new/save" var="action_url" />

<div class="row justify-content-center">
	<div class="col-6">
		<%--@elvariable id="car" type="it.univpm.advancedcode.cri.model.entities.Car"--%>
		<form:form class="form-signin" action="${action_url}" method="POST"
			modelAttribute="car">
        	<p class="text-danger">TUTTI I CAMPI SONO OBBLIGATORI</p>
			<h3 class="font-weight-bold text-center mb-3">Inserisci un nuovo
				veicolo</h3>

			<form:label path="targa">TARGA</form:label>
			<form:input path="targa" class="form-control mt-2" />

			<form:label path="marca">MARCA</form:label>
			<form:input path="marca" class="form-control mt-2" />

			<form:label path="modello">MODELLO</form:label>
			<form:input path="modello" class="form-control mt-2" />

			<form:label path="numeroTelaio">NUMERO TELAIO</form:label>
			<form:input path="numeroTelaio" class="form-control mt-2" />

			<form:label path="massa">MASSA</form:label>
			<form:input path="massa" class="form-control mt-2" />

			<form:label path="destinazioneUso">DESTINAZIONE D'USO</form:label>
			<form:input path="destinazioneUso" class="form-control mt-2" />

			<form:label path="numeroAssi">NUMERO ASSI</form:label>
			<form:input path="numeroAssi" class="form-control mt-2" />

			<form:label path="alimentazione">ALIMENTAZIONE</form:label>
			<form:input path="alimentazione" class="form-control mt-2" />

			<div class="d-flex justify-content-center">
				<input type="submit" value="Inserisci"
					class="mt-3 btn btn-primary col-4" />
			</div>
		</form:form>
	</div>
</div>

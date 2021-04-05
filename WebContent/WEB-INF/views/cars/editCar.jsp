<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/car/edit/save" var="action_url" />
<div class="row justify-content-center">
	<div class="col-6">

		<form:form name="modulo" class="form-signin" action="${action_url}" method="POST" modelAttribute="car" enctype="multipart/form-data">
			<h1 class="h3 mb-3 font-weight-normals">Modifica veicolo</h1>
				<form:input value="${car.id}" path="id" class="form-control mt-2" type="hidden"/>
				<form:label path="targa">TARGA</form:label>
				<form:input value="${car.targa}" path="targa" class="form-control mt-2" />
				<form:label path="marca">MARCA</form:label>
				<form:input value="${car.marca}" path="marca" class="form-control mt-2" />
				<form:label path="modello">MODELLO</form:label>
				<form:input value="${car.modello}" path="modello" class="form-control mt-2" />
				<form:label path="numeroTelaio">NUMERO TELAIO</form:label>
				<form:input value="${car.numeroTelaio}" path="numeroTelaio" class="form-control mt-2" />
				<form:label path="massa">MASSA</form:label>
				<form:input value="${car.massa}" path="massa" class="form-control mt-2" />
				<form:label path="destinazioneUso">DESTINAZIONE D'USO</form:label>
				<form:input value="${car.destinazioneUso}" path="destinazioneUso" class="form-control mt-2" />	
				<form:label path="numeroAssi">NUMERO ASSI</form:label>
				<form:input value="${car.numeroAssi}" path="numeroAssi" class="form-control mt-2" />
				<form:label path="alimentazione">ALIMENTAZIONE</form:label>
				<form:input value="${car.alimentazione}" path="alimentazione" class="form-control mt-2" />
				<div class="d-flex justify-content-center">
					<input type="submit" value="Applica"
						class="mt-3 btn btn-primary col-4" />
				</div>
		</form:form>
	</div>
</div>

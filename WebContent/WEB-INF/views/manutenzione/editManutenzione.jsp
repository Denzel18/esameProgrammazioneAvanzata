<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/manutenzione/edit/save" var="action_url"/>
<div class="col">

    <form:form name="modulo" action="${action_url}" method="POST" modelAttribute="manutenzione" enctype="multipart/form-data">
        <h1 class="h3 mb-3 font-weight-normals">Modifica manutenzione</h1>
		<%--@elvariable id="manutenzione" type="it.univpm.advancedcode.cri.model.entities.Manutenzione"--%>
        	<p class="text-danger">TUTTI I CAMPI SONO OBBLIGATORI</p>
            <hidden />
            <form:hidden value="${manutenzione.id}" path="id" class="form-control mt-2"/>
			<form:label path="costoManutenzione">Costo Manutenzione</form:label>
			<form:input value="${manutenzione.costoManutenzione}" path="costoManutenzione" class="form-control mt-2" />

            <br>

			<form:label path="tipoManutenzione">Tipo Manutenzione</form:label>
            <form:select path="tipoManutenzione" >
                <option value="Straordinaria" selected="selected">Manutenzione Straordinaria</option>
                <option value="Ordinaria">Manutenzione Ordinaria </option>
            </form:select>

            <br>
			
            <form:label path="veicolo">VEICOLO(TARGA)</form:label>
			<form:input value="${manutenzione.veicolo.targa}" path="veicolo" class="form-control mt-2" disabled="disabled" />

			<div class="d-flex justify-content-center">
				<input type="submit" value="Aggiorna"
					class="mt-3 btn btn-primary col-4" />
			</div>
    </form:form>
</div>



<script type="text/javascript">
    //Se vengono superati i controlli abilita l'inoltro del form (di default disabilitato)
    function controlloCampi() {

        var Id = document.getElementById("manutenzioneId").value;
        if (Id === "") {
            alert("Inserisci i campi richiesti!");
        }
        else document.modulo.submit();
    }
</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/manutenzione/new/save" var="action_url"/>
<div class="col">

    <form:form name="modulo" action="${action_url}" method="POST" modelAttribute="manutenzione">
        <h1 class="h3 mb-3 font-weight-normals">Inserisci manutenzione</h1>
		<%--@elvariable id="manutenzione" type="it.univpm.advancedcode.cri.model.entities.Manutenzione"--%>
        	<p class="text-danger">TUTTI I CAMPI SONO OBBLIGATORI</p>

			<form:label path="costoManutenzione">Costo Manutenzione</form:label>
			<form:input path="costoManutenzione" class="form-control mt-2" />

            <br>

			<form:label path="tipoManutenzione">Tipo Manutenzione</form:label>
            <form:select path="tipoManutenzione" class="form-control mt-2">
                <option value="Straordinaria" selected="selected">Manutenzione Straordinaria</option>
                <option value="Ordinaria">Manutenzione Ordinaria </option>
            </form:select>

            <br>
			
            <form:label name="veicolo" path="veicolo">VEICOLO(TARGA)</form:label>
            <form:select name="veicolo" path="veicolo" class="form-control mt-2" >
                <c:forEach items="${allCars}" var="car">
                    <option value="${car.id}">${car.targa}</option>
                </c:forEach>
            </form:select>

			<div class="d-flex justify-content-center">
				<input type="submit" value="Inserisci"
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/documentazione/edit/${documento_id}/allegati/file/new/save" var="file_action_url"/>

<%--@elvariable id="file" type="it.univpm.advancedcode.cri.model.entities.File"--%>
<form:form name="modulo" class="form-signin" action="${file_action_url}" method="POST" modelAttribute="file"
           enctype="multipart/form-data" onsubmit="return false">

    <div class="text-center mb-3">
        <h3 class="font-weight-bold">${pageTitle}</h3>
    </div>

    <label for="fileAllegato">Seleziona il file</label>
    <input type="file" name="fileAllegato" id="fileAllegato" class="form-control mt-2" required/>
    <small><b>MAX 1MB</b></small><br>

    <form:label path="descrizione">Descrizione</form:label>
    <form:input id="descriptionField" path="descrizione" class="form-control mt-2"/><br>

    <div class="form-check">
        <input class="form-check-input" type="radio" name="noDownloadable" id="false" value="false"
               checked>
        <label class="form-check-label" for="false">
            Scaricabile
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="noDownloadable" id="true" value="true">
        <label class="form-check-label" for="true">
            Non scaricabile
        </label>
    </div>

    <%--    <form:hidden path="id"/>--%>
    <%--    <form:hidden path="hide" value="false"/>--%>
    <%--    <form:hidden path="documento.id"/>--%>
    <input type="submit" value="Submit" class="mt-3 btn btn-lg btn-primary btn-block" onclick="controlloCampi()"/><br><br>
</form:form>

<script type="text/javascript">
    //Se vengono superati i controlli abilita l'inoltro del form (di default disabilitato)
    function controlloCampi() {

        var description = document.getElementById("descriptionField").value;

        if (description === "") {
            alert("Inserisci i campi richiesti!");
        }
        else document.modulo.submit();
    }
</script>
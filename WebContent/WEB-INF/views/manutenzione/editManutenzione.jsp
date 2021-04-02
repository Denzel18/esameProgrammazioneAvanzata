<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/manutenzione/edit/${manutenzione.id }/save" var="action_url"/>
<div class="col">

    <form:form name="modulo" action="${action_url}" method="POST" modelAttribute="manutenzione" onsubmit="return false">
        <h1 class="h3 mb-3 font-weight-normals">Modifica manutenzione</h1>

		<div class="form-group">
	        <label style="display: block;">Manutenzione Id
	            <input type="text" name="manutenzioneId" value="${manutenzione.id}" readonly="readonly" class="form-control mt-2" />
            </label>
        </div>
       
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Applica Modifiche!" onclick="controlloCampi()"/>
        <form:hidden path="id"/>
        <form:hidden path="hide"/>
        <form:hidden path="post.id"/>
        <form:hidden path="author.username"/>
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